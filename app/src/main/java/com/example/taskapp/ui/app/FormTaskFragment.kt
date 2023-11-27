package com.example.taskapp.ui.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task

import com.example.taskapp.databinding.FragmentFormtaskBinding
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormtaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var task: Task
    private var status: Status = Status.TODO
    private var newTask: Boolean = true

    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormtaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)

        reference = Firebase.database.reference
        auth = Firebase.auth

        initListeners()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            validateData()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbTodo -> status = Status.TODO
                R.id.rbDoing -> status = Status.DOING
                R.id.rbDone -> status = Status.DONE
            }
        }
    }

    private fun validateData() {
        val taskDescription = binding.editDescription.text.trim().toString()

        if (taskDescription.isNotEmpty()) {
            binding.progressBar.isVisible = true

            if (newTask) {
                task = Task()
                task.description = taskDescription
                task.status = status
                task.id = reference.database.reference.push().key ?: ""

            }
            saveTask()
        } else {
            showBottomSheet(message = getString(R.string.description_empty_form_task_fragment))
        }

        binding.progressBar.isVisible = false
    }

    private fun saveTask() {
        reference.child("tasks").child(auth.currentUser?.uid ?: "").child(task.id).setValue(task)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        R.string.text_toast_message_form_task_fragment,
                        Toast.LENGTH_SHORT
                    ).show()

                    if (newTask) {
                        findNavController().popBackStack()
                    }
                } else {
                    showBottomSheet(message = getString(R.string.error_generic))
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
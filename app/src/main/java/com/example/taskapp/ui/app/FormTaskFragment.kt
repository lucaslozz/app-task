package com.example.taskapp.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task

import com.example.taskapp.databinding.FragmentFormtaskBinding
import com.example.taskapp.utils.FirebaseHelper
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet

import com.google.firebase.database.ktx.database


class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormtaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var task: Task
    private var status: Status = Status.TODO
    private var newTask: Boolean = true


    private val args: FormTaskFragmentArgs by navArgs()

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

        getArgs()
        initListeners()
    }

    private fun getArgs() {
        args.task?.let {
            task = it

            configTask()
        }
    }

    private fun configTask() {

        newTask = false
        status = task.status
        binding.editDescription.setText(task.description)

        binding.textToolbar.setText(R.string.text_title_update_task)

        setStatus()
    }

    private fun setStatus() {
        binding.radioGroup.check(
            when (task.status) {
                Status.TODO -> R.id.rbTodo
                Status.DONE -> R.id.rbDone
                Status.DOING -> R.id.rbDoing
            }
        )
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
            }
            task.status = status
            task.description = taskDescription
            saveTask()
        } else {
            showBottomSheet(message = getString(R.string.description_empty_form_task_fragment))
        }

        binding.progressBar.isVisible = false
    }

    private fun saveTask() {
        FirebaseHelper.getDataBase().child("tasks").child(FirebaseHelper.getIdUser()).child(task.id)
            .setValue(task)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {

                    if (newTask) {
                        Toast.makeText(
                            requireContext(),
                            R.string.text_toast_message_form_task_fragment,
                            Toast.LENGTH_SHORT
                        ).show()

                        findNavController().popBackStack()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            R.string.text_toast_message_edit_form_task_fragment,
                            Toast.LENGTH_SHORT
                        ).show()
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
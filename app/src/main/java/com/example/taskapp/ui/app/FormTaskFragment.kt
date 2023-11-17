package com.example.taskapp.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskapp.R

import com.example.taskapp.databinding.FragmentFormtaskBinding
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet

class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormtaskBinding? = null
    private val binding get() = _binding!!
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

        initListeners()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val task = binding.editDescription.text.trim()

        if (task.isNotEmpty()) {
            Toast.makeText(requireContext(), "Tudo certo!", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = getString(R.string.description_empty_form_task_fragment))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
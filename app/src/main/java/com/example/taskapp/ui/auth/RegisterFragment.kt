package com.example.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentRegisterBinding
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edtEmail.text.trim()
        val password = binding.edtPassword.text.trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                Toast.makeText(requireContext(), "Tudo certo!", Toast.LENGTH_SHORT).show()
            } else {
                showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            }

        } else {
           showBottomSheet(message = getString(R.string.email_empty_register_fragment))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
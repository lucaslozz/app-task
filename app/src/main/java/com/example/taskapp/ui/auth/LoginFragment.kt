package com.example.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentLoginBinding
import com.example.taskapp.utils.showBottomSheet


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }

        binding.btnRecoveryAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoveryAccountFragment)
        }

        binding.btnAccess.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        val email = binding.edtEmail.text.trim()
        val password = binding.edtPassword.text.trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                findNavController().navigate(R.id.action_global_homeFragment2)
            } else {
                showBottomSheet(message = getString(R.string.password_empty))
            }

        } else {

           showBottomSheet(message = getString(R.string.email_empty))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
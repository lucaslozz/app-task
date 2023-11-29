package com.example.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentLoginBinding
import com.example.taskapp.utils.FirebaseHelper
import com.example.taskapp.utils.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

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
        auth = Firebase.auth
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
        val email = binding.edtEmail.text.trim().toString()
        val password = binding.edtPassword.text.trim().toString()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                binding.progressBar.isVisible = true
                loginUser(email, password)
            } else {
                showBottomSheet(message = getString(R.string.password_empty))
            }

        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }

    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment2)
                } else {
                    task.exception?.message?.let {
                        showBottomSheet(message = getString(FirebaseHelper.validError(it)))
                    }

                    binding.progressBar.isVisible = false
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
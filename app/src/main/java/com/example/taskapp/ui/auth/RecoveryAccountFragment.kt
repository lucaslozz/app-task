package com.example.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentRecoveryAccountBinding
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoveryAccountFragment : Fragment() {
    private var _binding: FragmentRecoveryAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoveryAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
        auth = Firebase.auth
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edtEmail.text.trim().toString()

        binding.progressBar.isVisible = true

        if (email.isNotEmpty()) {

            recoverAccountUser(email)
        } else {
            showBottomSheet(message = getString(R.string.text_info_recover_account_fragment))
        }

    }

    private fun recoverAccountUser(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            binding.progressBar.isVisible = false
            if (task.isSuccessful) {

                showBottomSheet(message = getString(R.string.text_message_recover_account_fragment))
            } else {
                Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
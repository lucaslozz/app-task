package com.example.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentRecoveryAccountBinding
import com.example.taskapp.utils.initToolbar
import com.example.taskapp.utils.showBottomSheet

class RecoveryAccountFragment : Fragment() {
    private var _binding: FragmentRecoveryAccountBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edtEmail.text.trim()

        if (email.isNotEmpty()) {
            Toast.makeText(requireContext(), "Tudo certo!", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = R.string.text_info_recover_account_fragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.taskapp.utils


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DialogTitle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.taskapp.R
import com.example.taskapp.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.initToolbar(toolbar: Toolbar) {
    val appCompatActivity = activity as? AppCompatActivity

    if (appCompatActivity != null) {
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.title = ""
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialog)
    val binding = BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.textTitle.text = getString(titleDialog ?: R.string.text_title_warning)
    binding.textMessage.text = message
    binding.btnBottomSheet.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}
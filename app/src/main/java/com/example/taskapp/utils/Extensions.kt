package com.example.taskapp.utils


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.initToolbar(toolbar: Toolbar){
    val appCompatActivity = activity as? AppCompatActivity

    if(appCompatActivity != null){
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.title = ""
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }
}
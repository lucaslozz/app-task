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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.FragmentTodoBinding
import com.example.taskapp.ui.adapter.TaskAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TodoFragment : Fragment(R.layout.fragment_todo) {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference
    private lateinit var list: List<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()



        auth = Firebase.auth
        reference = Firebase.database.reference

        getTasks { list -> initRecyclerViewTask(list) }
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_formTaskFragment2)
        }
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {
        taskAdapter = TaskAdapter(requireContext(), taskList) { task, option ->
            optionSelected(task, option)
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter = taskAdapter
    }

    private fun optionSelected(task: Task, option: Int) {
        Toast.makeText(requireContext(), "Opção selecionada: $option", Toast.LENGTH_SHORT).show()
    }


    private fun getTasks(list: (List<Task>) -> Unit) {
        reference.child("tasks").child(auth.currentUser?.uid ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val taskList = mutableListOf<Task>()
                    for (item in snapshot.children) {
                        val task = item.getValue<Task>()

                        if (task != null && task.status == Status.TODO) {
                            taskList.add(task)
                        }

                        binding.progressBar.isVisible = false

                        listEmpty(taskList)
                        list(taskList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), R.string.error_generic, Toast.LENGTH_SHORT)
                        .show()
                    list(emptyList())
                }
            })
    }

    private fun listEmpty(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            binding.textInfo.text = getText(R.string.text_list_task_empty)
        } else {
            binding.textInfo.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
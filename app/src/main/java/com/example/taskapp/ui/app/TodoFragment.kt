package com.example.taskapp.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.FragmentTodoBinding
import com.example.taskapp.ui.adapter.TaskAdapter

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

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
        initRecyclerViewTask(getTasks())
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_formTaskFragment2)
        }
    }

    private fun initRecyclerViewTask(taskList:List<Task>){
        taskAdapter = TaskAdapter(taskList)

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter=taskAdapter
    }

    private  fun getTasks()= listOf(
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
        Task("0","Criar nova tela do app",Status.TODO),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
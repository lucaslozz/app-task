package com.example.taskapp.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.FragmentDoneBinding
import com.example.taskapp.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewTask(getTasks())
    }

    private fun initRecyclerViewTask(taskList:List<Task>){
        taskAdapter = TaskAdapter(requireContext(),taskList){task,option->}

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter=taskAdapter
    }

    private  fun getTasks()= listOf(
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),
        Task("0","Criar nova tela do app", Status.DONE),


    )


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
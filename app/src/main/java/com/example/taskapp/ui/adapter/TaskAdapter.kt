package com.example.taskapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.CardTaskBinding


class TaskAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            CardTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var task = taskList[position]

        holder.binding.textDescription.text=task.description
    }


    override fun getItemCount() = taskList.size

    inner class TaskViewHolder( var binding: CardTaskBinding) : RecyclerView.ViewHolder(binding.root)
}
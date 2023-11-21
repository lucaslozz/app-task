package com.example.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.CardTaskBinding


class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>
) :
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
        val task = taskList[position]

        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    private fun setIndicators(task: Task, holder: TaskViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnArrowBackward.isVisible = false
            }

            Status.DOING -> {
                holder.binding.btnArrowForward.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_status_done
                    )
                )
                holder.binding.btnArrowBackward.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_status_todo
                    )
                )
            }

            Status.DONE -> {
                holder.binding.btnArrowForward.isVisible = false
            }


        }
    }

    override fun getItemCount() = taskList.size

    inner class TaskViewHolder(var binding: CardTaskBinding) : RecyclerView.ViewHolder(binding.root)
}
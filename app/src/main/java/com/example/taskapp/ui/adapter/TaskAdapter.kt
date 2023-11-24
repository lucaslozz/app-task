package com.example.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.data.model.Status
import com.example.taskapp.data.model.Task
import com.example.taskapp.databinding.CardTaskBinding


class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task, Int) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    companion object {
        val SELECT_BACK: Int = 0;
        val SELECT_REMOVE: Int = 1;
        val SELECT_EDIT: Int = 2;
        val SELECT_DETAILS: Int = 3;
        val SELECT_NEXT: Int = 4;
    }

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
        holder.binding.btnDelete.setOnClickListener {
            taskSelected(task, SELECT_REMOVE)
        }
        holder.binding.btnEdit.setOnClickListener {
            taskSelected(task, SELECT_EDIT)
        }
        holder.binding.btnDetails.setOnClickListener {
            taskSelected(task, SELECT_DETAILS)
        }

        when (task.status) {
            Status.TODO -> {
                holder.binding.btnArrowBackward.isVisible = false


                holder.binding.btnArrowForward.setOnClickListener {
                    taskSelected(task, SELECT_NEXT)
                }
                holder.binding.btnArrowBackward.setOnClickListener {
                    taskSelected(task, SELECT_BACK)
                }
            }

            Status.DOING -> {
                holder.binding.btnArrowForward.setOnClickListener {
                    taskSelected(task, SELECT_NEXT)
                }



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
                holder.binding.btnArrowBackward.setOnClickListener {
                    taskSelected(task, SELECT_BACK)
                }
                holder.binding.btnArrowForward.isVisible = false
            }


        }
    }


    override fun getItemCount() = taskList.size

    inner class TaskViewHolder(var binding: CardTaskBinding) : RecyclerView.ViewHolder(binding.root)
}
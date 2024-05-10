package com.example.todolistapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.entity.Task
import com.example.todolistapp.databinding.TaskCardBinding
import com.example.todolistapp.ui.fragment.HomePageFragmentDirections
import com.example.todolistapp.ui.viewmodel.HomePageViewModel
import com.example.todolistapp.utils.changePage

class TaskAdapter(private var mContext: Context, private var taskList: List<Task>, private var viewModel: HomePageViewModel) :
    RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    inner class TaskHolder(var design: TaskCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val binding: TaskCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.task_card, parent, false)
        return TaskHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = taskList.get(position)
        val binding = holder.design

        binding.taskObject = task

        if (task.taskActivated == 1) {
            binding.cardViewBackgroundColor = ContextCompat.getColor(mContext, R.color.bg_button)

            binding.checkboxChecked = true

            binding.textViewColor = ContextCompat.getColor(mContext, R.color.white)
            binding.imageViewColor = ContextCompat.getColor(mContext, R.color.white)
        } else if (task.taskActivated == 0) {
            binding.cardViewBackgroundColor = ContextCompat.getColor(mContext, android.R.color.transparent)

            binding.checkboxChecked = false

            binding.textViewColor = ContextCompat.getColor(mContext, R.color.black)
            binding.imageViewColor = ContextCompat.getColor(mContext, R.color.bg_button)
        }

        binding.cbTask.setOnClickListener {
            viewModel.setChecked(task)
        }

        binding.cardViewTask.setOnClickListener {
            val direction = HomePageFragmentDirections.actionHomePageFragmentToTaskDetailFragment(task = task)
            Navigation.changePage(it, direction)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}
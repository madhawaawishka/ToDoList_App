package com.example.todolistapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.todolistapp.R
import com.example.todolistapp.data.entity.Task
import com.example.todolistapp.data.repo.TaskRepository
import com.example.todolistapp.databinding.FragmentTaskDetailBinding
import com.example.todolistapp.ui.viewmodel.TaskDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {
    private lateinit var binding: FragmentTaskDetailBinding
    private val viewModel: TaskDetailViewModel by viewModels()

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_detail, container, false)
        binding.taskDetailFragment = this
        binding.taskDetailToolbarTitle = "Task Detail"

        val args: TaskDetailFragmentArgs by navArgs()
        val task = args.task

        binding.taskObject = task
        binding.taskDate = task.taskDate
        binding.taskTime = task.taskTime

        viewModel.taskDate.observe(viewLifecycleOwner) {
            binding.taskDate = it
        }

        viewModel.taskTime.observe(viewLifecycleOwner) {
            binding.taskTime = it
        }

        return binding.root
    }

    fun onDeleteTaskClicked(task: Task) {
        lifecycleScope.launch {
            taskRepository.deleteTask(task)
            // Optionally, update UI or perform other actions after deletion
        }
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun selectDate() {
        viewModel.selectDate(requireActivity().supportFragmentManager)
    }

    fun selectTime() {
        viewModel.selectTime(requireActivity().supportFragmentManager)
    }

    fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) {
        viewModel.updateTask(taskId, taskTitle, taskDate, taskTime)
        onBackPressed()
    }
}

package com.example.todolistapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentAddTaskBinding
import com.example.todolistapp.ui.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        binding.addTaskFragment = this
        binding.addTaskToolbarTitle = "Add Task"

        viewModel.taskDate.observe(viewLifecycleOwner) {
            binding.taskDate = it
        }

        viewModel.taskTime.observe(viewLifecycleOwner) {
            binding.taskTime = it
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AddTaskViewModel by viewModels()
        viewModel = tempViewModel
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

    fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        viewModel.createTask(taskTitle, taskDate, taskTime)
        onBackPressed()
    }
}
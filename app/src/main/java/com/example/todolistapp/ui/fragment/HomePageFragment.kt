package com.example.todolistapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentHomePageBinding
import com.example.todolistapp.ui.adapter.TaskAdapter
import com.example.todolistapp.ui.viewmodel.HomePageViewModel
import com.example.todolistapp.utils.changePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        binding.homePageFragment = this

        setStatusBarColor()

        binding.searchViewTask.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })

        viewModel.taskList.observe(viewLifecycleOwner) {
            val adapter = TaskAdapter(requireContext(), it, viewModel)
            binding.taskAdapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTasks()
    }

    private fun setStatusBarColor() {
        val background = resources.getDrawable(R.drawable.bg_layout)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        requireActivity().window.setBackgroundDrawable(background)
    }

    fun clickFab(view: View) {
        Navigation.changePage(view, R.id.action_homePageFragment_to_addTaskFragment)
    }
}
package com.example.todolistapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.entity.Task
import com.example.todolistapp.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var trepo: TaskRepository) : ViewModel() {
    val taskList = MutableLiveData<List<Task>>()

    init {
        loadTasks()
    }

    fun loadTasks() {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = trepo.loadTasks()
        }
    }

    fun search(queryWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = trepo.search(queryWord)
        }
    }

    fun setChecked(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            trepo.setChecked(task)
            loadTasks()
        }
    }
}
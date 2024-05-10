package com.example.todolistapp.ui.viewmodel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.repo.TaskRepository
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(var trepo: TaskRepository) : ViewModel() {
    val taskDate = MutableLiveData<String>()
    val taskTime = MutableLiveData<String>()

    fun selectDate(fragmentManager: FragmentManager) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.show(fragmentManager, "Date")

        datePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            taskDate.value = dateFormat.format(it)
        }
    }

    fun selectTime(fragmentManager: FragmentManager) {
        val timePicker = MaterialTimePicker.Builder()
            .setTitleText("Select Time")
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        timePicker.show(fragmentManager, "Time")

        timePicker.addOnPositiveButtonClickListener {
            taskTime.value = "${timePicker.hour}:${timePicker.minute}"
        }
    }

    fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) {
        CoroutineScope(Dispatchers.Main).launch {
            trepo.updateTask(taskId, taskTitle, taskDate, taskTime)
        }
    }
}
package com.example.todolistapp.data.repo

import com.example.todolistapp.data.datasource.TaskDataSource
import com.example.todolistapp.data.entity.Task

class TaskRepository(private val tds: TaskDataSource) {

    suspend fun deleteTask(task: Task) {
        tds.deleteTask(task) // Use 'tds' (TaskDataSource) instead of 'taskDataSource'
    }

    suspend fun loadTasks(): List<Task> = tds.loadTasks()

    suspend fun search(queryWord: String): List<Task> = tds.search(queryWord)

    suspend fun createTask(taskTitle: String, taskDate: String, taskTime: String) =
        tds.createTask(taskTitle, taskDate, taskTime)

    suspend fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) =
        tds.updateTask(taskId, taskTitle, taskDate, taskTime)

    suspend fun setChecked(task: Task) = tds.setChecked(task)
}

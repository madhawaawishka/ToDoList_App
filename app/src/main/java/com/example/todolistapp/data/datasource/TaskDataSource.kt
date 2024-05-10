package com.example.todolistapp.data.datasource

import com.example.todolistapp.data.entity.Task
import com.example.todolistapp.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(var taskDao: TaskDao) {
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun loadTasks(): List<Task> =
        withContext(Dispatchers.IO) {
            return@withContext taskDao.loadTasks()
        }

    suspend fun search(queryWord: String): List<Task> =
        withContext(Dispatchers.IO) {
            return@withContext taskDao.search(queryWord)
        }

    suspend fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        val newTask = Task(0, taskTitle, taskDate, taskTime, 0)
        taskDao.createTask(newTask)
    }

    suspend fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) {
        val updatedTask = Task(taskId, taskTitle, taskDate, taskTime, 0)
        taskDao.updateTask(updatedTask)
    }

    suspend fun setChecked(task: Task) {
        if (task.taskActivated == 0) {
            task.taskActivated = 1
        } else if (task.taskActivated == 1) {
            task.taskActivated = 0
        }
        taskDao.setChecked(task)
    }
}
package com.example.todolistapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task")
data class Task(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "task_id") var taskId: Int,
                @ColumnInfo(name = "task_title") var taskTitle: String,
                @ColumnInfo(name = "task_date") var taskDate: String,
                @ColumnInfo(name = "task_time") var taskTime: String,
                @ColumnInfo(name = "task_activated") var taskActivated: Int) : Serializable {
}
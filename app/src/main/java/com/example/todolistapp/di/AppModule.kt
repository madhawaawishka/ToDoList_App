package com.example.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.example.todolistapp.data.datasource.TaskDataSource
import com.example.todolistapp.data.repo.TaskRepository
import com.example.todolistapp.room.Database
import com.example.todolistapp.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTaskDataSource(taskDao: TaskDao): TaskDataSource {
        return TaskDataSource(taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(tds: TaskDataSource): TaskRepository {
        return TaskRepository(tds)
    }

    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        val db = Room.databaseBuilder(context, Database::class.java, "database.sqlite")
            .createFromAsset("database.sqlite").build()
        return db.getTaskDao()
    }
}
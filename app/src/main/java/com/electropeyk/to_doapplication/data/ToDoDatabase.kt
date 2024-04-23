package com.electropeyk.to_doapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.electropeyk.to_doapplication.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
package com.bugtsa.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bugtsa.todo.data.entities.TodoDAO
import com.bugtsa.todo.data.entities.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1
)

abstract class Database: RoomDatabase() {

    abstract fun getTodoDAO(): TodoDAO
}
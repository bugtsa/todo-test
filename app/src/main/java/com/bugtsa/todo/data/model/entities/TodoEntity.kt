package com.bugtsa.todo.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bugtsa.todo.domain.model.TodoDto

@Entity(tableName = "todo")
class TodoEntity(
    val id: Int,

    val completed: Boolean,

    val title: String,

    val userId: Int
) {

    @PrimaryKey(autoGenerate = true)
    var key: Long = 0L

    companion object {
        fun create (todoDto: TodoDto) = TodoEntity(
            todoDto.id,
            todoDto.completed,
            todoDto.title,
            todoDto.userId
        )
    }

}
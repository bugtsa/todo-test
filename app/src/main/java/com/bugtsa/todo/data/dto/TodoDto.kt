package com.bugtsa.todo.data.dto

import com.bugtsa.todo.data.entities.TodoEntity
import com.bugtsa.todo.data.remote.TodoRes
import com.bugtsa.todo.utils.DiffItem

class TodoDto(
    val id: Int,
    val completed: Boolean,
    val title: String,
    val userId: Int
) : DiffItem {

    override fun getItemId(): String {
        return id.toString()
    }

    override fun getDiff(): String {
        return id.toString().let { it }
    }

    companion object {
        private const val EMPTY = ""
        private const val ZERO = 0

        fun remoteCreate(todoResponse: TodoRes) = TodoDto(
            todoResponse.id ?: ZERO,
            todoResponse.completed ?: false,
            todoResponse.title ?: EMPTY,
            todoResponse.userId ?: ZERO
        )

        fun localCreate(todoEntity: TodoEntity) = TodoDto(
            todoEntity.id,
            todoEntity.completed,
            todoEntity.title,
            todoEntity.userId
        )

    }
}
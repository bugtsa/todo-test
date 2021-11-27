package com.bugtsa.todo.domain

import com.bugtsa.todo.data.model.entities.TodoEntity
import com.bugtsa.todo.data.model.remote.TodoRes
import com.bugtsa.todo.domain.model.TodoDto

object MapperDto {

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
package com.bugtsa.todo.domain.model

class TodoDto(
    val id: Int,
    val completed: Boolean,
    val title: String,
    val userId: Int
)
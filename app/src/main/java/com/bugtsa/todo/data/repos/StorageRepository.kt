package com.bugtsa.todo.data.repos

import com.bugtsa.todo.data.dto.TodoDto
import io.reactivex.Single

interface StorageRepository {

    fun observeTodoList(): Single<List<TodoDto>>

    fun saveTodoList(todoList: List<TodoDto>): Single<List<Long>>

    fun isEmptyTodoList(): Single<Boolean>
}
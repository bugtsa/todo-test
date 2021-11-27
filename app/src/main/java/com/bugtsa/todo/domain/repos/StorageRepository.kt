package com.bugtsa.todo.domain.repos

import com.bugtsa.todo.domain.model.TodoDto
import io.reactivex.Single

interface StorageRepository {

    fun observeTodoList(): Single<List<TodoDto>>

    fun saveTodoList(todoList: List<TodoDto>): Single<List<Long>>

    fun isEmptyTodoList(): Single<Boolean>
}
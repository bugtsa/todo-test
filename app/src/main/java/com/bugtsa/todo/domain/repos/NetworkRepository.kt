package com.bugtsa.todo.domain.repos

import com.bugtsa.todo.domain.model.TodoDto
import io.reactivex.Single

interface NetworkRepository {

    fun observeTodosList(): Single<List<TodoDto>>

    fun isAvailableNetwork(): Single<Boolean>
}
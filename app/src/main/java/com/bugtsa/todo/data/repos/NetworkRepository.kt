package com.bugtsa.todo.data.repos

import com.bugtsa.todo.data.dto.TodoDto
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NetworkRepository {

    fun observeTodosList(): Single<List<TodoDto>>

    fun isAvailableNetwork(): Single<Boolean>
}
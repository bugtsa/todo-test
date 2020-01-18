package com.bugtsa.todo.domain

import com.bugtsa.todo.data.dto.TodoDto
import io.reactivex.Flowable
import io.reactivex.Single

interface TodoInteractor {

    fun observeTodosList(): Single<List<TodoDto>>
}
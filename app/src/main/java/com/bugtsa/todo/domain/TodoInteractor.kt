package com.bugtsa.todo.domain

import io.reactivex.Single

interface TodoInteractor {

    fun observeTodosList(): Single<DataState>
}
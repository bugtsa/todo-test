package com.bugtsa.todo.domain

import com.bugtsa.todo.domain.model.DataState
import io.reactivex.Single

interface TodoInteractor {

    fun observeTodosList(): Single<DataState>
}
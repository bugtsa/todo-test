package com.bugtsa.todo.network

import com.bugtsa.todo.data.model.remote.TodoRes
import io.reactivex.Single
import retrofit2.http.GET

interface TodosApi {
    @GET("todos")
    fun requestTodo(): Single<List<TodoRes>>
}

package com.bugtsa.todo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TodoHttpHttpClientImpl : TodoHttpClient {

    private val todosApi: TodosApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        todosApi = retrofit.create(TodosApi::class.java)
    }

    override fun get(): TodosApi = todosApi

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val READ_TIMEOUT = 20L
        private const val CONNECTION_TIMEOUT = 60L
    }

}
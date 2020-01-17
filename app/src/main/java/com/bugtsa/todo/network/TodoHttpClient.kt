package com.bugtsa.todo.network

interface TodoHttpClient {

    fun get(): TodosApi
}
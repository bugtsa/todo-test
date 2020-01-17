package com.bugtsa.todo.network

import org.koin.dsl.bind
import org.koin.dsl.module

object NetworkModule {

    val module by lazy {
        module {
            single { TodoHttpHttpClientImpl() } bind TodoHttpClient::class
        }
    }
}
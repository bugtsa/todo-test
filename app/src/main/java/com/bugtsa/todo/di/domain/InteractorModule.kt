package com.bugtsa.todo.di.domain

import com.bugtsa.todo.domain.TodoWorkerInteractor
import com.bugtsa.todo.domain.TodoWorkerInteractorImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object InteractorModule {

    val module by lazy {
        module {
            single {
                TodoWorkerInteractorImpl(get(), get())
            } bind TodoWorkerInteractor::class
        }
    }
}
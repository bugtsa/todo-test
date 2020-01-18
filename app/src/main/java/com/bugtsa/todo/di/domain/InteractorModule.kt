package com.bugtsa.todo.di.domain

import com.bugtsa.todo.domain.TodoInteractor
import com.bugtsa.todo.domain.TodoInteractorImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object InteractorModule {

    val module by lazy {
        module {
            single {
                TodoInteractorImpl(get(), get())
            } bind TodoInteractor::class
        }
    }
}
package com.bugtsa.todo.di.presentation

import com.bugtsa.todo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val module by lazy {
        module {
            viewModel {
                MainViewModel(get())
            }
        }
    }
}
package com.bugtsa.todo.di

import android.app.Application
import com.bugtsa.todo.di.data.DatabaseConfigModule
import com.bugtsa.todo.di.data.RepositoryModule
import com.bugtsa.todo.di.domain.InteractorModule
import com.bugtsa.todo.di.presentation.ViewModelModule
import com.bugtsa.todo.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object TodosGraph {

    private lateinit var application: Application

    fun initialize(application: Application) {
        this.application = application

        assembleGraph()
    }

    private fun assembleGraph(): KoinApplication {
        val applicationGraph = listOf(
            DatabaseConfigModule.module,
            NetworkModule.module,
            RepositoryModule.module,
            RepositoryModule.connectivityCheckerModule,
            InteractorModule.module,
            ViewModelModule.module
        )

        return startKoin {
            androidLogger()
            androidContext(application)
            modules(applicationGraph)
        }
    }
}
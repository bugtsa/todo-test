package com.bugtsa.todo.di.data

import com.bugtsa.todo.data.repos.NetworkRepository
import com.bugtsa.todo.data.repos.NetworkRepositoryImpl
import com.bugtsa.todo.data.repos.StorageRepository
import com.bugtsa.todo.data.repos.StorageRepositoryImpl
import com.bugtsa.todo.network.utils.connectivity.ConnectivityChecker
import com.bugtsa.todo.network.utils.connectivity.ConnectivityCheckerImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoryModule {

    val module by lazy {
        module {
            single { NetworkRepositoryImpl(get(), get()) } bind NetworkRepository::class

            single { StorageRepositoryImpl(get())} bind StorageRepository::class
        }
    }

    val connectivityCheckerModule = module {
        single { ConnectivityCheckerImpl(get()) } bind ConnectivityChecker::class
    }
}
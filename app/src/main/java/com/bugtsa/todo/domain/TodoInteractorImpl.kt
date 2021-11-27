package com.bugtsa.todo.domain

import com.bugtsa.todo.domain.MapperState.fromState
import com.bugtsa.todo.domain.MapperState.toState
import com.bugtsa.todo.domain.repos.NetworkRepository
import com.bugtsa.todo.domain.repos.StorageRepository
import com.bugtsa.todo.domain.model.DataState
import io.reactivex.Completable
import io.reactivex.Single

class TodoInteractorImpl(
    private val networkRepository: NetworkRepository,
    private val storageRepository: StorageRepository
) : TodoInteractor {

    override fun observeTodosList(): Single<DataState> {
        return networkRepository.isAvailableNetwork()
            .flatMap { isAvailable ->
                if (isAvailable) {
                    observeNetworkTodoList()
                } else {
                    storageRepository.observeTodoList()
                        .map { todoList ->
                            if (todoList.isNotEmpty()) {
                                DataState.OfflineFilledList(todoList.map { it.toState() })
                            } else {
                                DataState.OfflineEmptyList
                            }
                        }
                }
            }
            .flatMap { dataState ->
                val completable = when {
                    dataState is DataState.OnlineFilledList && dataState.isEmptyStorage -> {
                        storageRepository.saveTodoList(dataState.list.map { it.fromState() })
                            .flatMapCompletable { Completable.complete() }
                    }
                    else -> Completable.complete()
                }
                completable
                    .andThen(Single.fromCallable { dataState })
            }
    }

    private fun observeNetworkTodoList(): Single<DataState.OnlineFilledList> {
        return Single.zip(networkRepository.observeTodosList(),
            storageRepository.isEmptyTodoList()
        ) { list, isEmptyStorage ->
            DataState.OnlineFilledList(list.map { it.toState() }, isEmptyStorage)
        }
    }
}
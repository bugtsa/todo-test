package com.bugtsa.todo.domain

import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.data.repos.NetworkRepository
import com.bugtsa.todo.data.repos.StorageRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

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
                                DataState.OfflineFilledList(todoList)
                            } else {
                                DataState.OfflineEmptyList
                            }
                        }
                }
            }
            .flatMap { dataState ->
                val completable = when {
                    dataState is DataState.OnlineFilledList && dataState.isEmptyStorage -> {
                        storageRepository.saveTodoList(dataState.list)
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
            storageRepository.isEmptyTodoList(),
            BiFunction { list, isEmptyStorage ->
                DataState.OnlineFilledList(list, isEmptyStorage)
            })
    }
}

sealed class DataState {
    object OfflineEmptyList : DataState()
    data class OfflineFilledList(val list: List<TodoDto>) : DataState()
    data class OnlineFilledList(
        val list: List<TodoDto>,
        val isEmptyStorage: Boolean
    ) : DataState()
}
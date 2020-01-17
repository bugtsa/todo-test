package com.bugtsa.todo.domain

import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.data.repos.NetworkRepository
import com.bugtsa.todo.data.repos.StorageRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import timber.log.Timber

class TodoWorkerInteractorImpl(
    private val networkRepository: NetworkRepository,
    private val storageRepository: StorageRepository
) :
    TodoWorkerInteractor {

    override fun observeTodosList(): Single<List<TodoDto>> {
        return networkRepository.isAvailableNetwork()
            .flatMap { isAvailable ->
                if (isAvailable) {
                    observeNetworkTodoList()
                } else {
                    storageRepository.observeTodoList()
                        .map { ListTodosWrapper(it) }
                }
            }
            .flatMap { listTodos ->
                val completable = if (listTodos.isEmptyStorage) {
                    storageRepository.saveTodoList(listTodos.list)
                        .map { list ->
                            list.forEach() {
                                Timber.d("index $it")
                            }
                        }.flatMapCompletable { Completable.complete() }
                } else {
                    Completable.complete()
                }
                completable
                    .andThen(Single.fromCallable { listTodos.list })
            }
    }

    private fun observeNetworkTodoList(): Single<ListTodosWrapper> {
        return Single.zip(networkRepository.observeTodosList(),
            storageRepository.isEmptyTodoList(),
            BiFunction { list, isEmptyStorage ->
                ListTodosWrapper(list, isEmptyStorage)
            })
    }
}

data class ListTodosWrapper(val list: List<TodoDto>, val isEmptyStorage: Boolean = false)
package com.bugtsa.todo.data.repos

import com.bugtsa.todo.domain.MapperDto
import com.bugtsa.todo.domain.model.TodoDto
import com.bugtsa.todo.domain.repos.NetworkRepository
import com.bugtsa.todo.network.TodoHttpHttpClientImpl
import com.bugtsa.todo.network.utils.connectivity.ConnectivityCheckerImpl
import io.reactivex.Single

class NetworkRepositoryImpl(
    private val todosClientImpl: TodoHttpHttpClientImpl,
    private val connectivityChecker: ConnectivityCheckerImpl
) :
    NetworkRepository {

    override fun observeTodosList(): Single<List<TodoDto>> {
        return todosClientImpl.get().requestTodo()
            .map { list -> list.map { MapperDto.remoteCreate(it) } }
    }

    override fun isAvailableNetwork(): Single<Boolean> {
        return connectivityChecker.observeIsInternetAvailable()
            .firstOrError()
    }
}
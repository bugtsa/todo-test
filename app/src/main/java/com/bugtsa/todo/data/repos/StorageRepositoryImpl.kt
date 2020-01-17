package com.bugtsa.todo.data.repos

import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.data.entities.TodoDAO
import com.bugtsa.todo.data.entities.TodoEntity
import io.reactivex.Completable
import io.reactivex.Single

class StorageRepositoryImpl(private val todoDao: TodoDAO) : StorageRepository {
    override fun observeTodoList(): Single<List<TodoDto>> {
        return todoDao.observeTodoList()
            .map { list -> list.map { TodoDto.localCreate(it) } }
    }

    override fun saveTodoList(todoList: List<TodoDto>): Single<List<Long>> {
        return todoDao
            .insertParameters(todoList.map { TodoEntity.create(it) })
    }

    override fun isEmptyTodoList(): Single<Boolean> {
        return todoDao.getCountTodoList()
            .map { it <= 0 }
    }
}
package com.bugtsa.todo.data.repos

import com.bugtsa.todo.domain.model.TodoDto
import com.bugtsa.todo.data.model.entities.TodoDAO
import com.bugtsa.todo.data.model.entities.TodoEntity
import com.bugtsa.todo.domain.MapperDto
import com.bugtsa.todo.domain.repos.StorageRepository
import io.reactivex.Single

class StorageRepositoryImpl(private val todoDao: TodoDAO) : StorageRepository {
    override fun observeTodoList(): Single<List<TodoDto>> {
        return todoDao.observeTodoList()
            .map { list -> list.map { MapperDto.localCreate(it) } }
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
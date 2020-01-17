package com.bugtsa.todo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.data.remote.TodoRes
import com.bugtsa.todo.domain.TodoWorkerInteractor
import com.bugtsa.todo.global.ErrorHandler
import com.bugtsa.todo.presentation.RxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(todoWorkerInteractor: TodoWorkerInteractor) : RxViewModel() {

    private val todosListLiveData = MutableLiveData<List<TodoDto>>()
    fun observeTodosList(): LiveData<List<TodoDto>> = todosListLiveData

    init {
        todoWorkerInteractor.observeTodosList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ todosListLiveData.value = it }, ErrorHandler::handle)
            .also(::addDispose)
    }
}

package com.bugtsa.todo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.domain.TodoInteractor
import com.bugtsa.todo.global.ErrorHandler
import com.bugtsa.todo.global.rx.SchedulersProvider
import com.bugtsa.todo.presentation.RxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(todoInteractor: TodoInteractor) : RxViewModel() {

    private val todosListLiveData = MutableLiveData<List<TodoDto>>()
    fun observeTodosList(): LiveData<List<TodoDto>> = todosListLiveData

    init {
        todoInteractor.observeTodosList()
            .subscribeOn(SchedulersProvider.io())
            .observeOn(SchedulersProvider.ui())
            .subscribe ({ todosListLiveData.value = it }, ErrorHandler::handle)
            .also(::addDispose)
    }
}

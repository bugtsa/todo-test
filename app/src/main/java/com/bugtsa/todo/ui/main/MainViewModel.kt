package com.bugtsa.todo.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.domain.DataState
import com.bugtsa.todo.domain.TodoInteractor
import com.bugtsa.todo.global.ErrorHandler
import com.bugtsa.todo.global.rx.SchedulersProvider
import com.bugtsa.todo.presentation.RxViewModel

class MainViewModel(todoInteractor: TodoInteractor) : RxViewModel() {

    private val todosListLiveData = MutableLiveData<List<TodoDto>>()
    fun observeTodosList(): LiveData<List<TodoDto>> = todosListLiveData

    private val progressStateLiveData = MutableLiveData<Int>()
    fun observeProgressState(): LiveData<Int> = progressStateLiveData

    private val checkInternetLiveData = MutableLiveData<Unit>()
    fun observeCheckInternet(): LiveData<Unit> = checkInternetLiveData

    init {
        progressStateLiveData.value = View.VISIBLE
        todoInteractor.observeTodosList()
            .subscribeOn(SchedulersProvider.io())
            .observeOn(SchedulersProvider.ui())
            .subscribe({ dataState ->
                progressStateLiveData.value = View.GONE
                when (dataState) {
                    is DataState.OfflineEmptyList ->
                        checkInternetLiveData.value = Unit
                    is DataState.OfflineFilledList ->
                        todosListLiveData.value = dataState.list
                    is DataState.OnlineFilledList ->
                        todosListLiveData.value = dataState.list
                }
            }, ErrorHandler::handle)
            .also(::addDispose)
    }
}

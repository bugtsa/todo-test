package com.bugtsa.todo.presentation

import androidx.lifecycle.ViewModel
import com.bugtsa.todo.global.ErrorHandler
import com.bugtsa.todo.global.contracts.ErrorObservableOwner
import com.bugtsa.todo.global.contracts.OnBackPressable
import com.hadilq.liveevent.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel : ViewModel(), OnBackPressable, ErrorObservableOwner {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val errorLiveData = LiveEvent<String>()

    fun addDispose(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun addDisposes(vararg disposables: Disposable) = disposables.forEach(::addDispose)

    open fun unsubscribe() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        unsubscribe()
    }

    override fun onBackPressed() {}

    override fun observeErrorLiveData() = errorLiveData

    protected open fun handleError(
        throwable: Throwable,
        showError: Boolean = true,
        defaultErrorMessage: String? = null
    ) {
        ErrorHandler.handle(throwable)
        if (showError) (throwable.message ?: defaultErrorMessage)?.let(errorLiveData::postValue)
    }
}
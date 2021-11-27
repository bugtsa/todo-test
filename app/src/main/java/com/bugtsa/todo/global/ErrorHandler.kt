package com.bugtsa.todo.global

import org.koin.core.KoinComponent
import timber.log.Timber

object ErrorHandler : KoinComponent {

    fun handle(throwable: Throwable) {
        Timber.e(throwable)
    }
}
package com.bugtsa.todo.domain.model

import com.bugtsa.todo.ui.models.TodoState

sealed class DataState {
    object OfflineEmptyList : DataState()
    data class OfflineFilledList(val list: List<TodoState>) : DataState()
    data class OnlineFilledList(
        val list: List<TodoState>,
        val isEmptyStorage: Boolean
    ) : DataState()
}
package com.bugtsa.todo.ui.models

import com.bugtsa.todo.utils.DiffItem

class TodoState(
    val id: Int,
    val completed: Boolean,
    val title: String,
    val userId: Int
) : DiffItem {

    override fun getItemId(): String {
        return id.toString()
    }

    override fun getDiff(): String {
        return id.toString()
    }
}
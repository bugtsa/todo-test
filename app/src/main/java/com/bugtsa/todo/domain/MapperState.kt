package com.bugtsa.todo.domain

import com.bugtsa.todo.domain.model.TodoDto
import com.bugtsa.todo.ui.models.TodoState

object MapperState {

    fun TodoDto.toState() : TodoState =
        TodoState(
            this.id,
            this.completed,
            this.title,
            this.userId
        )

    fun TodoState.fromState() : TodoDto =
        TodoDto(
            this.id,
            this.completed,
            this.title,
            this.userId
        )
}
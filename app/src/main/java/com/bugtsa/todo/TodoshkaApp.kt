package com.bugtsa.todo

import android.app.Application
import com.bugtsa.todo.di.TodosGraph

class TodoshkaApp: Application() {

    override fun onCreate() {
        super.onCreate()

        TodosGraph.initialize(this)
    }
}
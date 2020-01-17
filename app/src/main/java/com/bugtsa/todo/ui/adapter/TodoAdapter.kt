package com.bugtsa.todo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bugtsa.todo.R
import com.bugtsa.todo.data.dto.TodoDto
import com.bugtsa.todo.utils.autoNotify
import kotlinx.android.synthetic.main.item_todo.view.*
import kotlin.properties.Delegates

class TodoAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoDto> by Delegates.observable(initialValue = listOf(), onChange = { property, oldValue, newValue ->
        autoNotify(oldValue, newValue)
    })

    //region ================= Implements Methods =================

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todoItem: TodoDto = todoList[position]
        holder.title.text = todoItem.title
        holder.status.text = if (todoItem.completed) "SUCCESS" else "PROGRESS"
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_todo, parent, false)
        )
    }

    //endregion

    fun setItems(itemList: List<TodoDto>) {
        this.todoList = itemList
    }

    //region ================= View Holder =================

    class ViewHolder(item: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(item) {
        var title: TextView = item.title_todo
        var status: TextView = item.status_todo
    }

    //endregion
}
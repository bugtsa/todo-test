package com.bugtsa.todo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bugtsa.todo.R
import com.bugtsa.todo.ui.models.TodoState
import com.bugtsa.todo.utils.autoNotify
import kotlin.properties.Delegates

class TodoAdapter(private val context: Context) :
    androidx.recyclerview.widget.RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList: List<TodoState> by Delegates.observable(
        initialValue = listOf(),
        onChange = { _, oldValue, newValue ->
            autoNotify(oldValue, newValue)
        })

    //region ================= Implements Methods =================

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todoItem: TodoState = todoList[position]
        holder.title.text = todoItem.title
        holder.status.text = if (todoItem.completed) {
            context.getString(R.string.completed_status)
        } else {
            context.getString(R.string.not_completed_status)
        }
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

    fun setItems(itemList: List<TodoState>) {
        this.todoList = itemList
    }

    //region ================= View Holder =================

    class ViewHolder(item: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(item) {
        var title: TextView = item.findViewById(R.id.title_todo)
        var status: TextView = item.findViewById(R.id.status_todo)
    }

    //endregion
}
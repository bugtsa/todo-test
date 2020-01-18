package com.bugtsa.todo.ui.main

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bugtsa.todo.R
import com.bugtsa.todo.ui.adapter.SpacesItemDecoration
import com.bugtsa.todo.ui.adapter.TodoAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var todoAdapter: TodoAdapter? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindView()
        bindViewModel()
    }

    private fun bindView() {
        recyclerView = vTodoList
        todoAdapter = TodoAdapter(requireContext())

        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        vTodoList.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        val space = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 8f,
            resources.displayMetrics
        ).toInt()
        recyclerView.addItemDecoration(
            SpacesItemDecoration(
                space
            )
        )
        recyclerView.adapter = todoAdapter
    }

    private fun bindViewModel() {
        viewModel.observeTodosList().observe(viewLifecycleOwner, Observer {
            todoAdapter?.setItems(it)
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

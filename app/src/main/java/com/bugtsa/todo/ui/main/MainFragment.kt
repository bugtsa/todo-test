package com.bugtsa.todo.ui.main

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bugtsa.todo.R
import com.bugtsa.todo.databinding.MainFragmentBinding
import com.bugtsa.todo.ui.adapter.SpacesItemDecoration
import com.bugtsa.todo.ui.adapter.TodoAdapter
import com.bugtsa.todo.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private var todoAdapter: TodoAdapter? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel by viewModel<MainViewModel>()

    private val binding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        bindViewModel()
    }

    private fun bindView() {
        recyclerView = binding.vTodoList
        todoAdapter = TodoAdapter(requireContext())

        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.vTodoList.layoutManager = mLayoutManager
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
        viewModel.observeTodosList().observe(viewLifecycleOwner) {
            binding.vTodoList.visibility = View.VISIBLE
            binding.vCheckInternet.visibility = View.GONE
            todoAdapter?.setItems(it)
        }
        viewModel.observeProgressState().observe(viewLifecycleOwner) { visibleState ->
            binding.vProgressBar.visibility = visibleState
        }
        viewModel.observeCheckInternet().observe(viewLifecycleOwner) {
            binding.vTodoList.visibility = View.GONE
            binding.vCheckInternet.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

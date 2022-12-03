package com.utn.usersapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class FragmentList : Fragment(R.layout.fragment_list), FragmentsDependencies {
    private lateinit var refresh: SwipeRefreshLayout
    private lateinit var list: RecyclerView
    private lateinit var adapter: UserAdapter

    override var navigate: Navigate? = null

    override lateinit var usersApi: UsersApi

    private lateinit var viewModel: ViewModelList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelsFactory = ViewModelsFactory(usersApi)
        viewModel = ViewModelProvider(this, viewModelsFactory).get(ViewModelList::class.java)

        viewModel.users.observe(this) { users ->
            refresh.isRefreshing = false
            adapter.setUsers(users)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        navigate = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = view.findViewById(R.id.list)

        adapter = UserAdapter(requireContext()) { user ->
            navigate!!.goToDetail(user.id.value ?: user.id.name)
        }
        list.adapter = adapter
        refresh = view.findViewById(R.id.refresh)
        refresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refresh()
            }
        })
        viewModel.getUsers()
    }

    private fun refresh() {
        refresh.isRefreshing = true
        viewModel.getUsers()
    }

}














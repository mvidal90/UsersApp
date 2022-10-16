package com.utn.usersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ActivityList : AppCompatActivity() {

    private lateinit var refresh: SwipeRefreshLayout
    private lateinit var list: RecyclerView
    private lateinit var adapter: UserAdapter

    private lateinit var repositoryUsers: UsersApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryUsers = UsersApi(this)

        list = findViewById(R.id.list)

        adapter = UserAdapter(this) { user ->
            val intent = Intent(this, ActivityDetail::class.java)
            intent.putExtra("fullName", "${user.name.title} ${user.name.first} ${user.name.last}")
            intent.putExtra("image", user.picture.large)
            intent.putExtra("age", user.dob.age.toString())
            intent.putExtra("country", user.location.country)
            intent.putExtra("email", user.email)
            intent.putExtra("phone", user.phone)
            intent.putExtra("address", "${user.location.street.name} ${user.location.street.number}, ${user.location.city} (${user.location.postcode})")

            startActivity(intent)
        }

        list.adapter = adapter

        refresh = findViewById(R.id.refresh)
        refresh.setOnRefreshListener { refresh() }

        refresh()
    }

    private fun refresh(){
        repositoryUsers.getUsers(
            errorCallback = { t ->
                refresh.isRefreshing = false
                Log.e("USERSAPP", "Hubo un error en la conexión con el servidor", t)
            },
            successCallback = { users ->
                refresh.isRefreshing = false
                adapter.setUsers(users)
            }
        )
    }
}
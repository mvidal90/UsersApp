package com.utn.usersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class ActivityList : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var adapter: UserAdapter

    private lateinit var repositoryUsers: UsersApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryUsers = UsersApi(this)

        list = findViewById(R.id.list)

        adapter = UserAdapter(this) {
            //val intent = Intent(this, ActivityDetail::class.java)
            //startActivity(intent)
        }
        list.adapter = adapter

        refresh()
    }

    private fun refresh(){
        repositoryUsers.getUsers(
            errorCallback = { t ->
                Log.e("USERSAPP", "No se pudieron obtener los usuarios", t)
            },
            successCallback = { users ->
                adapter.setUsers(users)
            }
        )
    }
}
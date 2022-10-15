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

        adapter = UserAdapter(this) { user ->
            val intent = Intent(this, ActivityDetail::class.java)
            intent.putExtra("fullName", "${user.name.title} ${user.name.first} ${user.name.last}")
            intent.putExtra("image", user.picture.large)
            intent.putExtra("age", user.dob.age.toString())
            intent.putExtra("country", user.location.country)
            intent.putExtra("email", user.email)
            intent.putExtra("phone", user.phone)
            intent.putExtra("address", "${user.location.street}, ${user.location.city} ( ${user.location.postcode}")

            startActivity(intent)
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
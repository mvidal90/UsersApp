package com.utn.usersapp

import android.app.Application

class UsersApp : Application() {
    lateinit var usersApi: UsersApi private set

    override fun onCreate() {
        super.onCreate()
        usersApi = UsersApi(this)
    }
}

package com.utn.usersapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelDetail (
    val idUser: String,
) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun getUser() {

    }

}
package com.utn.usersapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelList(
    private val usersApi: UsersApi
): ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>> = _users

    private val _errors = MutableLiveData<Throwable>()
    val errors: LiveData<Throwable> = _errors

    fun getUsers() {
        usersApi.getUsers(
            successCallback = { list ->
                _users.value = list
            },
            errorCallback = { t ->
                _errors.value = t
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("UsersApp", "ViewModel was Cleared")
    }
}
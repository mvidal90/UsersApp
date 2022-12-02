package com.utn.usersapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class ViewModelsFactory(
    val usersApi: UsersApi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        when (modelClass) {
            ViewModelList::class.java ->  return ViewModelList(usersApi) as T
            else -> throw IllegalArgumentException("Can not find ViewModel type")
        }
    }
}

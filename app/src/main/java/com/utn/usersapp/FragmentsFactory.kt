package com.utn.usersapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class FragmentsFactory(
    val navigate: Navigate,
    val userApi: UsersApi
) :  FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragment = super.instantiate(classLoader, className)

        if (fragment is FragmentsDependencies) {
            fragment.navigate = navigate
            fragment.usersApi = userApi
        }
        return fragment
    }
}
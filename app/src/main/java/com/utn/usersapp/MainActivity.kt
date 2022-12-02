package com.utn.usersapp;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity(), Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {

        val usersApp = application as UsersApp

        supportFragmentManager.fragmentFactory = FragmentsFactory(
            navigate = this,
            usersApi = usersApp.usersApi
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (haveNotFragments()) {
            showList()
        }
    }

    private fun haveNotFragments(): Boolean {
        return supportFragmentManager.findFragmentById(R.id.container) == null
    }

    private fun showList() {
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            FragmentList::class.java.name
        )

        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }

    override fun goToDetail(id: Id) {
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            FragmentDetail::class.java.name
        )

        fragment.arguments = bundleOf(FragmentDetail.ID_USER to id)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}

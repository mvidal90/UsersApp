package com.utn.usersapp

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

public class UsersApi(context: Context) {
    private val baseUrl = "https://randomuser.me/"
    private val usersService: UsersService

    init {
        val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val moshiRetrofitConvert = MoshiConverterFactory.create(moshi)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(moshiRetrofitConvert)
                .build()

        usersService = retrofit.create(UsersService::class.java)
    }


    fun getUsers(
        successCallback: (List<User>) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        val call = usersService.find()

        call.enqueue(object : Callback<UsersResult> {
            override fun onResponse(
                call: Call<UsersResult>,
                response: Response<UsersResult>
            ) {
                if (response.isSuccessful) {
                    val data: UsersResult? = response.body()

                    if (data == null) {
                        throw IllegalStateException("Llamada exitosa, pero no esta la lista de Usuarios")
                    } else {
                        successCallback(data.results)
                    }
                }
            }

            override fun onFailure(call: Call<UsersResult>, err: Throwable) {
                errorCallback(err)
            }
        })
    }

}

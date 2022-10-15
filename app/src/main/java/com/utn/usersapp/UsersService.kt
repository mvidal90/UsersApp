package com.utn.usersapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {
    @GET("/api")
    fun find(
        @Query("results")
        limit: Int = 20
    ): Call<UsersResult>
}

data class UsersResult(
    val results: List<User>,
    val info: Info
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
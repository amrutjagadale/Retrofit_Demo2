package com.example.retrofit_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserServices {
    @GET("https://dummyjson.com/posts")
    suspend fun fetchUsers(@Path("posts") title: Int) : UserModel

    companion object{
        fun getInstance(): UserServices{
            val retrofit = Retrofit.Builder()
            val userServices = retrofit.baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserServices::class.java)

            return userServices
        }
    }
}
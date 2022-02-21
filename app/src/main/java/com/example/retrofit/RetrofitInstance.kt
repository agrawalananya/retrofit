package com.example.retrofit

import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:ToDoApi by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoApi::class.java)
    }
}
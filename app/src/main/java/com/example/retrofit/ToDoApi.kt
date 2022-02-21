package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ToDoApi {

    @GET("/todos")
    suspend fun getTodo():Response<List<ToDo>>
}
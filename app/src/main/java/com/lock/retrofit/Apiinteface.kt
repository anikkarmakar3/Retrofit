package com.lock.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Apiinteface {
    @GET("posts")
    fun getData(): Call<List<DataItemModel>>
}
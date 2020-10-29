package com.lara.mvvmmtvshows.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.episodate.com/api/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiClient {

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
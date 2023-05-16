package com.example.bismillahjadi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val  BASE_URL ="https://api.themoviedb.org/"
    private const val API_KEY = "5eadf04193e2a9bcea8a8e8f4cfa89e2"

    val instance : ApiService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
package br.com.appforge.kotlintimers.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val jsonPlaceApi by lazy{
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")//posts
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceAPI::class.java)
    }
}
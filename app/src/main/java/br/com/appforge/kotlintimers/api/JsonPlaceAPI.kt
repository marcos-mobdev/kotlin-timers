package br.com.appforge.kotlintimers.api

import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceAPI {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}
package br.com.appforge.kotlintimers.api

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
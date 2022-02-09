package com.lorrane.animelist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Services {
    private const val BASE_URL = "https://api.jikan.moe/v4/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val animeService = retrofit.create(AnimeRepositoy::class.java)

}
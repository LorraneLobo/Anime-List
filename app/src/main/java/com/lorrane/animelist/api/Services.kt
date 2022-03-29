package com.lorrane.animelist.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Services {
    private const val BASE_URL = "https://api.jikan.moe/v4/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().callTimeout(1, TimeUnit.MINUTES).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val animeService = retrofit.create(AnimeService::class.java)

}
package com.lorrane.animelist.api

import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeRepositoy {

    @GET("top/anime")
    fun getTopAnimes(@Query("page") page: Int): Call<Page<List<Anime>>>

}
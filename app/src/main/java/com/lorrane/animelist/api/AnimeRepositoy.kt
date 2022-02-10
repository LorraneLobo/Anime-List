package com.lorrane.animelist.api

import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import retrofit2.Call
import retrofit2.http.GET

interface AnimeRepositoy {

    @GET("top/anime")
    fun getTopAnimes(): Call<Page<List<Anime>>>

    @GET("recommendations/anime")
    fun getRecommendationAnime(): Call<Page<List<Anime>>>

}
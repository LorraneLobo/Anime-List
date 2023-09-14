package com.lorrane.animelist.data.api

import com.lorrane.animelist.domain.Anime
import com.lorrane.animelist.domain.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("top/anime")
    fun getTopAnimes(@Query("page") page: Int): Call<Page<List<Anime>>>

    @GET("anime/{id}")
    fun getDetalhesAnime(@Path("id") id: Int) : Call<Page<Anime>>

}

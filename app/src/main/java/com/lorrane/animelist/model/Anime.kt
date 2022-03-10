package com.lorrane.animelist.model

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("mal_id") val id: Int,
    val title: String,
    val episodes: Int,
    val score: Double,
    val synopsis: String,
    val year: Int,
    val trailer: Trailer,
    val rank: String,
    val popularity: Int,
    val duration: String,
    val status:String,
    val genres: List<Genero>,
    val images: Imagem
)
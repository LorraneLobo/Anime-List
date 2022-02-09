package com.lorrane.animelist.model

data class Anime(
    val id: Int,
    val title: String,
    val episodes: Int,
    val score: Double,
    val synopsis: String,
    val year: Int,
    val genres: List<Genero>,
    val images: Imagem
)
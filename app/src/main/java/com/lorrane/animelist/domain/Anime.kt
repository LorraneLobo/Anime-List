package com.lorrane.animelist.domain

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
        val status: String,
        val genres: List<Genero>,
        val images: Imagem
) {

    val hasTrailer: Boolean
        get() = trailer.url.isNullOrEmpty().not()

    fun getGenresFormatted() = genres.take(4).joinToString(separator = "  •  ") { it.name }
}

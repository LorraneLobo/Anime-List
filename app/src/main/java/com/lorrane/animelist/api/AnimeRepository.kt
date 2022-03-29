package com.lorrane.animelist.api

import javax.inject.Inject

class AnimeRepository @Inject constructor(val animeService: AnimeService) {

    fun getTopAnimes(page: Int) = animeService.getTopAnimes(page)

    fun getDetalhesAnime(id: Int) = animeService.getDetalhesAnime(id)
}
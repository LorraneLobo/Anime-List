package com.lorrane.animelist.data.repository

import com.lorrane.animelist.data.api.AnimeService
import javax.inject.Inject

class AnimeRepository @Inject constructor(val animeService: AnimeService) {

    fun getTopAnimes(page: Int) = animeService.getTopAnimes(page)

    fun getDetalhesAnime(id: Int) = animeService.getDetalhesAnime(id)
}

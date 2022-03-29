package com.lorrane.animelist.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lorrane.animelist.model.Anime
import javax.inject.Inject

class PreferenceManagerUtil @Inject constructor(val sharedPref: SharedPreferences) {

    fun addFavorite(anime: Anime) {
        val animeList = getFavoritos().toMutableList()
        if (animeList.none { param -> anime.id == param.id }) {
            animeList.add(anime)
            saveFavoritos(sharedPref, animeList)
        }
    }

    fun removeFavorite(anime: Anime) {
        val animeList = getFavoritos().toMutableList()
        val index = animeList.indexOfFirst { param -> anime.id == param.id }
        if (index != -1) {
            animeList.removeAt(index)
            saveFavoritos(sharedPref, animeList)
        }
    }

    fun isFavorite(animeId: Int): Boolean {
        return getFavoritos().any { param -> animeId == param.id }
    }

    fun getFavoritos(): List<Anime> {
        val prefAnime = sharedPref.getString(FAVORITOS_KEY, null) ?: return emptyList()
        return Gson().fromJson(prefAnime, object : TypeToken<List<Anime>>() {}.type)
    }

    private fun saveFavoritos(sharedPreferences: SharedPreferences, animeList: List<Anime>) {
        with(sharedPreferences.edit()) {
            putString(FAVORITOS_KEY, Gson().toJson(animeList))
            commit()
        }
    }

    companion object {
        private const val FAVORITOS_KEY = "FAVORITOS"
    }
}
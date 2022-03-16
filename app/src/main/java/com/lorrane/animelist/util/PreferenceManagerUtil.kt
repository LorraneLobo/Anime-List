package com.lorrane.animelist.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lorrane.animelist.model.Anime

object PreferenceManagerUtil {
    private const val FAVORITOS_KEY = "FAVORITOS"

    fun addFavorite(anime: Anime, context: Context) {
        val sharedPref = context.getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE)

        val animeList = getFavoritos(context).toMutableList()
        if (animeList.none { param -> anime.id == param.id }) {
            animeList.add(anime)
            saveFavoritos(sharedPref, animeList)
        }
    }

    fun removeFavorite(anime: Anime, context: Context) {
        val sharedPref = context.getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE)
        val animeList = getFavoritos(context).toMutableList()
        val index = animeList.indexOfFirst { param -> anime.id == param.id }
        if (index != -1) {
            animeList.removeAt(index)
            saveFavoritos(sharedPref, animeList)
        }
    }

    fun isFavorite(animeId: Int, context: Context): Boolean {
        return getFavoritos(context).any { param -> animeId == param.id }
    }

    fun getFavoritos(context: Context): List<Anime> {
        val sharedPref = context.getSharedPreferences(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE)
        val prefAnime = sharedPref.getString(FAVORITOS_KEY, null) ?: return emptyList()
        return Gson().fromJson(prefAnime, object : TypeToken<List<Anime>>() {}.type)
    }

    private fun saveFavoritos(sharedPreferences: SharedPreferences, animeList: List<Anime>) {
        with(sharedPreferences.edit()) {
            putString(FAVORITOS_KEY, Gson().toJson(animeList))
            commit()
        }
    }
}
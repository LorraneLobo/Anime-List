package com.lorrane.animelist.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.util.PreferenceManagerUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val preferenceManagerUtil: PreferenceManagerUtil
) : ViewModel() {

    private val _animeList: MutableLiveData<List<Anime>> = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> = _animeList

    init {
        getFavoritos()
    }

    fun getFavoritos() {
        _animeList.postValue(preferenceManagerUtil.getFavoritos())

    }

    fun removerFavorito(anime: Anime) {
        preferenceManagerUtil.removeFavorite(anime)
        getFavoritos()
    }
}
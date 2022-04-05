package com.lorrane.animelist.ui.detalhesAnime

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lorrane.animelist.api.AnimeRepository
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import com.lorrane.animelist.util.PreferenceManagerUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetalhesAnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val preferenceManagerUtil: PreferenceManagerUtil
) : ViewModel() {

    private var _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _anime: MutableLiveData<Anime> = MutableLiveData<Anime>()
    val anime: LiveData<Anime> = _anime

    private val _error: MutableLiveData<String> = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    fun getDetalhesAnime(animeId: Int) {
        _loading.postValue(true)
        _isFavorite.postValue(preferenceManagerUtil.isFavorite(animeId))
        animeRepository.getDetalhesAnime(animeId)
            .enqueue(object : Callback<Page<Anime>> {
                override fun onResponse(call: Call<Page<Anime>>, response: Response<Page<Anime>>) {
                    response.body()?.data?.let {
                        _anime.postValue(it)
                    }
                    _loading.postValue(false)
                }

                override fun onFailure(call: Call<Page<Anime>>, t: Throwable) {
                    _error.postValue(t.message)
                    _loading.postValue(false)
                }

            })
    }

    fun toggleFavorito(){
        anime.value?.let { anime ->
            if (isFavorite.value == false) {
                preferenceManagerUtil.addFavorite(anime)
            } else {
                preferenceManagerUtil.removeFavorite(anime)
            }
            _isFavorite.postValue(isFavorite.value?.not())
        }
    }
}
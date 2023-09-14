package com.lorrane.animelist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lorrane.animelist.data.repository.AnimeRepository
import com.lorrane.animelist.domain.Anime
import com.lorrane.animelist.domain.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(private val animeRepository: AnimeRepository) : ViewModel() {
    var hasNextPage: Boolean = false
        private set
    var currentPage: Int = 1
        private set

    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _animeList: MutableLiveData<List<Anime>> = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> = _animeList

    init {
        getTopAnime()
    }

    fun getTopAnime() {
        _loading.postValue(true)
        animeRepository.getTopAnimes(currentPage)
            .enqueue(object : Callback<Page<List<Anime>>> {
                override fun onResponse(
                        call: Call<Page<List<Anime>>>,
                        response: Response<Page<List<Anime>>>
                ) {
                    response.body()?.let {
                        hasNextPage = it.pagination.hasNextPage
                        currentPage++
                        val lista = _animeList.value.orEmpty().toMutableList()
                        lista.addAll(it.data)
                        _animeList.postValue(lista)
                    }
                    _loading.postValue(false)
                }

                override fun onFailure(call: Call<Page<List<Anime>>>, t: Throwable) {
                    _error.postValue(t.message)
                    _loading.postValue(false)
                }
            })
    }
}

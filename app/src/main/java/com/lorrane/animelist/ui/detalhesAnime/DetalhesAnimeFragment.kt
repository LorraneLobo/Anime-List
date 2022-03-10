package com.lorrane.animelist.ui.detalhesAnime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.FragmentDetalhesAnimeBinding
import com.lorrane.animelist.databinding.FragmentTopAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import retrofit2.Callback
import com.lorrane.animelist.ui.topAnime.TopAnimeAdapter
import retrofit2.Call
import retrofit2.Response

class DetalhesAnimeFragment : Fragment(){

    private lateinit var binding: FragmentDetalhesAnimeBinding
    private val args: DetalhesAnimeFragmentArgs by navArgs()
//    private val adapterDetalhesAnime: TopAnimeAdapter = TopAnimeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalhesAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Configura RecyclerView Detalhe Animes
//        binding.recyclerDetalhesAnimes.apply {
//            val gridLayoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
//            layoutManager = gridLayoutManager
//            adapter = adapterTopAnimes
//            addOnScrollListener(criarScrollListener(gridLayoutManager))
//        }

        //Recupera Top Animes
        getDetalhesAnime()
    }

    private fun getDetalhesAnime() {
        Services.animeService.getDetalhesAnime(args.animeId)
            .enqueue(object : Callback<Page<Anime>> {
                override fun onResponse(call: Call<Page<Anime>>, response: Response<Page<Anime>>) {
                    response.body()?.let {
                        carregarAnime(it.data)
                    }
                }

                override fun onFailure(call: Call<Page<Anime>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun carregarAnime(anime: Anime){
//        binding.textScore.text = anime.score.toString()
//        binding.textRank.text = anime.rank
//        binding.textTituloDetalheAnime.text = anime.title
//        binding.textAno.text = anime.year.toString()
//        binding.textStatus.text = anime.status
//        binding.textEpisodios.text = anime.episodes.toString()
//        binding.textDuracao.text = anime.duration
//        binding.textGeneros.text = anime.genres.toString()
//        binding.textSinopse.text = anime.synopsis
//

    }

}
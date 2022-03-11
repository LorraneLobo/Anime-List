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
import com.bumptech.glide.Glide
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

class DetalhesAnimeFragment : Fragment() {

    private lateinit var binding: FragmentDetalhesAnimeBinding
    private val args: DetalhesAnimeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalhesAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recupera Detalhes do Anime
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

    fun carregarAnime(anime: Anime) {
        anime.run {
            binding.textScore.text = score.toString()
            binding.textRank.text = "# $rank"
            binding.textPopularity.text = "# $popularity"
            binding.textTituloDetalheAnime.text = title
            binding.textAno.text = year.toString()
            binding.textStatus.text = status.substringBefore(" ")
            binding.textEpisodios.text = episodes.toString()
            binding.textDuracao.text = duration
            binding.textGeneros.text = genres.take(4).joinToString(separator = " - ") { it.name }
            binding.textSinopse.text = synopsis

            Glide.with(requireContext()).load(anime.images.jpg.largeImageUrl)
                .into(binding.imageDetalheAnime)
        }

    }

}
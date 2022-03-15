package com.lorrane.animelist.ui.detalhesAnime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.FragmentDetalhesAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import retrofit2.Call
import retrofit2.Callback
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
                        binding.apply {
                            progressBar.hide()
                            scrollView.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onFailure(call: Call<Page<Anime>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun carregarAnime(anime: Anime) {
        anime.run {
            binding.apply {
                textScore.text = score.toString()
                textRank.text = "# $rank"
                textPopularity.text = "# $popularity"
                textTituloDetalheAnime.text = title
                textAno.text = year.toString()
                textStatus.text = status.substringBefore(" ")
                textEpisodios.text = episodes.toString()
                textDuracao.text = duration
                textGeneros.text = genres.take(4).joinToString(separator = " - ") { it.name }
                textSinopse.text = synopsis
                imageTrailer.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(trailer.url))
                    startActivity(browserIntent)
                }
                Glide.with(requireContext()).load(trailer.imagem.largeImageUrl).into(imageTrailer)
                Glide.with(requireContext()).load(anime.images.jpg.largeImageUrl)
                    .into(imageDetalheAnime)
            }

        }

    }


}
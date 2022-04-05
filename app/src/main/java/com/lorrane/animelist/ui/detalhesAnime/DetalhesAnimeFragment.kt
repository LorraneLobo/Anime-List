package com.lorrane.animelist.ui.detalhesAnime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lorrane.animelist.R
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.FragmentDetalhesAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import com.lorrane.animelist.ui.topAnime.TopAnimeViewModel
import com.lorrane.animelist.util.ARQUIVO_PREFERENCIA
import com.lorrane.animelist.util.PreferenceManagerUtil
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class DetalhesAnimeFragment : Fragment() {

    private lateinit var binding: FragmentDetalhesAnimeBinding
    private val args: DetalhesAnimeFragmentArgs by navArgs()
    private val viewModel: DetalhesAnimeViewModel by viewModels()

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
        viewModel.getDetalhesAnime(args.animeId)

        binding.apply {
            lifecycleOwner = this@DetalhesAnimeFragment
            vm = viewModel

            //Salvar ou excluir favorito
            fabFavoritos.setOnClickListener {
                viewModel.toggleFavorito()
            }

            imageTrailer.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.anime.value?.trailer?.url)))
            }
        }

        viewModel.run {
            isFavorite.observe(viewLifecycleOwner){
                binding.fabFavoritos.imageTintList =
                    ColorStateList.valueOf(if (it) Color.RED else Color.WHITE)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
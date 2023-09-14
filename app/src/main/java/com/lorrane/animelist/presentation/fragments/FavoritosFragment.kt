package com.lorrane.animelist.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lorrane.animelist.databinding.FragmentFavoritosBinding
import com.lorrane.animelist.domain.Anime
import com.lorrane.animelist.presentation.adapters.FavoritosAdapter
import com.lorrane.animelist.presentation.viewmodel.FavoritosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritosFragment : Fragment(), FavoritosAdapter.OnClickListener {

    private lateinit var binding: FragmentFavoritosBinding
    private val viewModel: FavoritosViewModel by viewModels()

    private val adapterFavoritos: FavoritosAdapter = FavoritosAdapter(listener = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
//            lifecycleOwner = this@TopAnimeFragment
//            vm = viewModel

            //Configura RecyclerView Animes Favoritos
            recyclerFavoritos.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterFavoritos
            }
        }

        viewModel.run {
            animeList.observe(viewLifecycleOwner) {
                adapterFavoritos.setData(it)
            }
        }
    }

    override fun onClickAnime(anime: Anime) {
        findNavController().navigate(FavoritosFragmentDirections.actionFavoritosFragmentToDetalhesAnimeFragment(anime.id))
    }

    override fun onClickRemoverFavorito(anime: Anime) {
        viewModel.removerFavorito(anime)
    }
}

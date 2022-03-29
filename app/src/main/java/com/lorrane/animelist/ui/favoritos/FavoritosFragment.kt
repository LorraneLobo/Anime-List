package com.lorrane.animelist.ui.favoritos

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lorrane.animelist.databinding.FragmentFavoritosBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.util.PreferenceManagerUtil
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class FavoritosFragment : Fragment(), FavoritosAdapter.OnClickListener {

    private lateinit var binding: FragmentFavoritosBinding

    @Inject
    lateinit var preferenceManagerUtil: PreferenceManagerUtil
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
        //Configura RecyclerView Animes Favoritos
        binding.recyclerFavoritos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterFavoritos
        }

        //Recupera Animes Favoritos
        adapterFavoritos.setData(preferenceManagerUtil.getFavoritos())
    }

    override fun onClickAnime(anime: Anime) {
        findNavController().navigate(FavoritosFragmentDirections.actionFavoritosFragmentToDetalhesAnimeFragment(anime.id))
    }

    override fun onClickRemoverFavorito(anime: Anime) {
        preferenceManagerUtil.removeFavorite(anime)
    }
}
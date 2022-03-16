package com.lorrane.animelist.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.FragmentFavoritosBinding
import com.lorrane.animelist.databinding.FragmentTopAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import com.lorrane.animelist.ui.detalhesAnime.DetalhesAnimeFragment
import com.lorrane.animelist.util.PAGE_SIZE
import com.lorrane.animelist.util.PreferenceManagerUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritosFragment : Fragment() {

    private lateinit var binding: FragmentFavoritosBinding

    private val adapterFavoritos: FavoritosAdapter = FavoritosAdapter()

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
        adapterFavoritos.appendData(PreferenceManagerUtil.getFavoritos(requireContext()))
    }

}
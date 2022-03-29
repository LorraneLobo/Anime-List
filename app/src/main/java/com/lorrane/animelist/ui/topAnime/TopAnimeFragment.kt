package com.lorrane.animelist.ui.topAnime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lorrane.animelist.databinding.FragmentTopAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.util.PAGE_SIZE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAnimeFragment : Fragment(), TopAnimeAdapter.OnClickListener {

    private val viewModel: TopAnimeViewModel by viewModels()
    private lateinit var binding: FragmentTopAnimeBinding
    private val adapterTopAnimes: TopAnimeAdapter = TopAnimeAdapter(listener = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@TopAnimeFragment
            vm = viewModel

            //Configura RecyclerView Top Animes
            recyclerTopAnimes.apply {
                val gridLayoutManager =
                    GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                layoutManager = gridLayoutManager
                adapter = adapterTopAnimes
                addOnScrollListener(criarScrollListener(gridLayoutManager))
            }
        }

        viewModel.run {
            animeList.observe(viewLifecycleOwner) {
                adapterTopAnimes.setData(it)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun criarScrollListener(layoutManager: GridLayoutManager): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.hasNextPage && viewModel.isLoading.value == false) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        viewModel.getTopAnime()
                    }
                }
            }
        }

    override fun onClickAnime(anime: Anime) {
        findNavController().navigate(
            TopAnimeFragmentDirections.actionTopAnimeFragmentToDetalhesAnimeFragment(
                anime.id
            )
        )
    }
}
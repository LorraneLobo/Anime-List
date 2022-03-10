package com.lorrane.animelist.ui.topAnime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lorrane.animelist.R
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.FragmentTopAnimeBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import com.lorrane.animelist.ui.detalhesAnime.DetalhesAnimeFragment
import com.lorrane.animelist.util.PAGE_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopAnimeFragment : Fragment(), TopAnimeAdapter.OnClickListener {

    private var isLoading: Boolean = false
    private var hasNextPage: Boolean = false
    private var currentPage: Int = 1

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
        //Configura RecyclerView Top Animes
        binding.recyclerTopAnimes.apply {
            val gridLayoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            layoutManager = gridLayoutManager
            adapter = adapterTopAnimes
            addOnScrollListener(criarScrollListener(gridLayoutManager))
        }

        //Recupera Top Animes
        getTopAnime()
    }

    fun getTopAnime() {
        isLoading = true
        Services.animeService.getTopAnimes(currentPage)
            .enqueue(object : Callback<Page<List<Anime>>> {
                override fun onResponse(
                    call: Call<Page<List<Anime>>>,
                    response: Response<Page<List<Anime>>>
                ) {
                    response.body()?.let {
                        hasNextPage = it.pagination.hasNextPage
                        currentPage++
                        adapterTopAnimes.appendData(it.data)
                    }
                    isLoading = false
                }

                override fun onFailure(call: Call<Page<List<Anime>>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    isLoading = false
                }
            })
    }

    fun criarScrollListener(layoutManager: GridLayoutManager): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (hasNextPage && !isLoading) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        getTopAnime()
                    }
                }
            }
        }

    override fun onClickAnime(anime: Anime) {
        findNavController().navigate(TopAnimeFragmentDirections.actionTopAnimeFragmentToDetalhesAnimeFragment(anime.id))
    }
}
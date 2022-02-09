package com.lorrane.animelist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lorrane.animelist.api.Services
import com.lorrane.animelist.databinding.ActivityMainBinding
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.model.Page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getData()

    }

    fun getData() {
        Services.animeService.getTopAnimes().enqueue(object : Callback<Page<List<Anime>>> {
            override fun onResponse(
                call: Call<Page<List<Anime>>>,
                response: Response<Page<List<Anime>>>
            ) {
                response.body()?.data?.forEach {
                    binding.textHello.text = binding.textHello.text.toString().plus(it.title + "\n")
                }
            }

            override fun onFailure(call: Call<Page<List<Anime>>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
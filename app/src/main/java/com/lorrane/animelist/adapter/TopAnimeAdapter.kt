package com.lorrane.animelist.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lorrane.animelist.R

class TopAnimeAdapter : RecyclerView.Adapter<TopAnimeAdapter.MyViewHolder>() {

 public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     val imageCapaAnime: ImageView = itemView.findViewById(R.id.imageCapaAnime)
     val textTitulo: TextView = itemView.findViewById(R.id.textTitulo)
     val textNota: TextView = itemView.findViewById(R.id.textNota)
     val textGenero: TextView = itemView.findViewById(R.id.textGenero)

 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
package com.lorrane.animelist.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lorrane.animelist.R
import com.lorrane.animelist.model.Anime

class TopAnimeAdapter(var listaAnime: List<Anime> = emptyList()) : RecyclerView.Adapter<TopAnimeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemLista: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_top_anime, parent, false);
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anime:Anime = listaAnime.get(position)
        holder.textTitulo.text = anime.title
        holder.textGenero.text = anime.genres.firstOrNull()?.name
        holder.textNota.text = anime.score.toString()

        Glide.with(holder.itemView.context).load(anime.images.jpg.imageUrl).into(holder.imageCapaAnime)

    }

    override fun getItemCount() = listaAnime.size

    fun setData(dataList: List<Anime>) {
        this.listaAnime = dataList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     val imageCapaAnime: ImageView = itemView.findViewById(R.id.imageCapaAnime)
     val textTitulo: TextView = itemView.findViewById(R.id.textTitulo)
     val textNota: TextView = itemView.findViewById(R.id.textNota)
     val textGenero: TextView = itemView.findViewById(R.id.textGenero)

    }
}
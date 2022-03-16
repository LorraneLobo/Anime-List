package com.lorrane.animelist.ui.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lorrane.animelist.R
import com.lorrane.animelist.model.Anime

class FavoritosAdapter(var listaFavoritos: MutableList<Anime> = mutableListOf()) : RecyclerView.Adapter<FavoritosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_favoritos, parent, false)
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anime:Anime = listaFavoritos.get(position)
        holder.textTitulo.text = anime.title
        holder.textEpisodios.text = anime.episodes.toString()

        Glide.with(holder.itemView.context).load(anime.images.jpg.imageUrl).into(holder.imageAnime)
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }

    fun appendData(dataList: List<Anime>) {
        this.listaFavoritos.addAll(dataList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageAnime: ImageView = itemView.findViewById(R.id.imageAnime)
        val textTitulo: TextView = itemView.findViewById(R.id.textTituloAnime)
        val textEpisodios: TextView = itemView.findViewById(R.id.textEpisodiosAnime)
        val buttonFavoritar: ImageButton = itemView.findViewById(R.id.btnFavoritar)
    }


}
package com.lorrane.animelist.ui.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lorrane.animelist.R
import com.lorrane.animelist.model.Anime
import com.lorrane.animelist.ui.topAnime.TopAnimeAdapter

class FavoritosAdapter(var listaFavoritos: MutableList<Anime> = mutableListOf()) : RecyclerView.Adapter<FavoritosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_favoritos, parent, false)
        return FavoritosAdapter.MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anime:Anime = listaFavoritos.get(position)
        holder.textTitulo.text = anime.title
        holder.textDescricao.text = anime.synopsis

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
        val textDescricao: TextView = itemView.findViewById(R.id.textDescricaoAnime)
        val buttonFavoritar: Button = itemView.findViewById(R.id.btnFavoritar)
    }


}
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

class FavoritosAdapter(
    var listaFavoritos: MutableList<Anime> = mutableListOf(),
    val listener: OnClickListener
) : RecyclerView.Adapter<FavoritosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_favoritos, parent, false)
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anime: Anime = listaFavoritos.get(position)
        holder.itemView.setOnClickListener { listener.onClickAnime(anime) }
        holder.textTitulo.text = anime.title
        holder.textEpisodios.text = anime.episodes.toString()
        holder.buttonFavoritar.setOnClickListener {
            listaFavoritos.remove(anime)
            listener.onClickRemoverFavorito(anime)
            notifyDataSetChanged()
        }

        Glide.with(holder.itemView.context).load(anime.images.jpg.imageUrl).into(holder.imageAnime)
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }

    fun setData(dataList: List<Anime>) {
        this.listaFavoritos = dataList.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageAnime: ImageView = itemView.findViewById(R.id.imageAnime)
        val textTitulo: TextView = itemView.findViewById(R.id.textTituloAnime)
        val textEpisodios: TextView = itemView.findViewById(R.id.textEpisodiosAnime)
        val buttonFavoritar: ImageButton = itemView.findViewById(R.id.btnFavoritar)
    }

    interface OnClickListener {
        fun onClickAnime(anime: Anime)
        fun onClickRemoverFavorito(anime: Anime)
    }


}
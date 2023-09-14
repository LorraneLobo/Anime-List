package com.lorrane.animelist.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.lorrane.animelist.domain.Genero

@BindingAdapter("showView")
fun showView(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("hideView")
fun hideView(view: View, hide: Boolean) {
    view.visibility = if (hide) View.GONE else View.VISIBLE
}

@BindingAdapter("showViewIfFalseInvisible")
fun showViewIfFalseInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let { Glide.with(view.context).load(it).into(view) }
}

@BindingAdapter("formatGenres")
fun formatGenres(view: TextView, genres: List<Genero>) {
    view.text = genres.take(4).joinToString(separator = "  •  ") { it.name }
}

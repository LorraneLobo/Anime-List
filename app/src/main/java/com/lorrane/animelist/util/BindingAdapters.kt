package com.lorrane.animelist.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("showView")
fun showView(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}
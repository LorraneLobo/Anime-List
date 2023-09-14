package com.lorrane.animelist.domain

import com.google.gson.annotations.SerializedName

data class Page<T>(val pagination: Pagination, val data: T)

data class Pagination(
    @SerializedName("last_visible_page") val lastVisiblePage: Int,
    @SerializedName("has_next_page") val hasNextPage: Boolean,
)

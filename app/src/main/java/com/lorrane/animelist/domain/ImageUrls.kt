package com.lorrane.animelist.domain

import com.google.gson.annotations.SerializedName

data class ImageUrls(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String,
    @SerializedName("medium_image_url") val mediumImageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String,
)

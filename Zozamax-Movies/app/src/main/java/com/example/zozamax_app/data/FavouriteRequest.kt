package com.example.zozamax_app.data

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("media_id") val mediaId: Int,
    @SerializedName("favorite") val favorite: Boolean
)

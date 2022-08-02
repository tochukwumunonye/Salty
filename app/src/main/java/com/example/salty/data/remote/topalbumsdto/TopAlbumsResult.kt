package com.example.salty.data.remote.topalbumsdto

import com.google.gson.annotations.SerializedName

data class TopAlbumsResult(
    @SerializedName("feed")
    val feed: Feed
)
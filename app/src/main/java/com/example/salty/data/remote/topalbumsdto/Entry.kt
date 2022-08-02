package com.example.salty.data.remote.topalbumsdto

import com.example.salty.domain.model.Album
import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("im:artist")
    val imArtist: ImArtist,
    @SerializedName("im:image")
    val imImage: List<ImImage>,
    @SerializedName("im:name")
    val imName: ImName,
)


fun Entry.toAlbum(): Album {
    return Album(
        albumArtist = imArtist.label,
        albumImage = imImage.last().label,
        albumName =  imName.label
    )
}

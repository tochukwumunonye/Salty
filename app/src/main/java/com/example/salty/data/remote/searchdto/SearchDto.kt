package com.example.salty.data.remote.searchdto

import com.example.salty.domain.model.SearchAlbum
import com.google.gson.annotations.SerializedName

data class SearchDto(
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("trackName")
    val trackName: String,
)

fun SearchDto.toSearchAlbum(): SearchAlbum {
    return SearchAlbum(
        artistName = artistName,
        artworkUrl = artworkUrl100,
        trackName = trackName
    )
}
/**

fun Entry.toAlbum(): Album {
return Album(
albumArtist = imArtist.label,
albumImage = imImage.last().label,
albumName =  imName.label
)
}

 */
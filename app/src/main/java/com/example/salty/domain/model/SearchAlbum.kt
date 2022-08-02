package com.example.salty.domain.model

import com.example.salty.data.local.entity.AlbumEntity

data class SearchAlbum(
    val artistName: String,
   val artworkUrl: String,
    val trackName: String?
)


fun SearchAlbum.toAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        artist = artistName,
        image = artworkUrl,
        name = trackName,

    )
}
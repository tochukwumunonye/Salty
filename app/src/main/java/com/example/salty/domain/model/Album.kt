package com.example.salty.domain.model

import com.example.salty.data.local.entity.AlbumEntity
import com.example.salty.data.remote.topalbumsdto.ImImage

data class Album(
    //val albumArtist: AlbumArtist,
    //val albumImage: List<AlbumImage>,
    //val albumName: AlbumName
    val albumArtist: String,
    val albumImage: String,
    val albumName: String
)

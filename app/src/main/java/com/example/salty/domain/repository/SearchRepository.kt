package com.example.salty.domain.repository

import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(query: String) : List<SearchAlbum>
    suspend fun insertIntoDatabase(album: SearchAlbum)
    suspend fun deleteAlbum(album: SearchAlbum)
    suspend fun getSearchedAlbums(): Flow<List<SearchAlbum>>
}
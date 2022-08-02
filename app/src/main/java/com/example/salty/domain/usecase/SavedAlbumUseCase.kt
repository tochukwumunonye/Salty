package com.example.salty.domain.usecase

import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SavedAlbumUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(): Flow<List<SearchAlbum>> {
        return repository.getSearchedAlbums()
    }
}
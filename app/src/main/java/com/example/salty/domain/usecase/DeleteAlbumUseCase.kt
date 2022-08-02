package com.example.salty.domain.usecase

import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.repository.SearchRepository

class DeleteAlbumUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(album: SearchAlbum) {
        repository.deleteAlbum(album)
    }
}
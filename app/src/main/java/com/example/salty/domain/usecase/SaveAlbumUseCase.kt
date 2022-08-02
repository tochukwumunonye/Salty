package com.example.salty.domain.usecase

import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.repository.AlbumRepository
import com.example.salty.domain.repository.SearchRepository

class SaveAlbumUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(album: SearchAlbum) {
        repository.insertIntoDatabase(album)
    }

}


/**
 * class DeleteTrackedFood(
private val repository: TrackerRepository
) {

suspend operator fun invoke(trackedFood: TrackedFood) {
repository.deleteTrackedFood(trackedFood)
}
}
 */
package com.example.salty.di

import com.example.salty.domain.repository.AlbumRepository
import com.example.salty.domain.repository.SearchRepository
import com.example.salty.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideAlbumUseCases(
        repository: SearchRepository,
    ): AlbumUseCase {
        return AlbumUseCase(
            searchUseCase = SearchUseCase(repository),
            saveAlbum = SaveAlbumUseCase(repository),
            savedAlbum = SavedAlbumUseCase(repository),
            deleteAlbum = DeleteAlbumUseCase(repository)
            )
    }
}


/**
 *
@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

@ViewModelScoped
@Provides
fun provideTrackerUseCases(
repository: TrackerRepository,
preferences: Preferences
): TrackerUseCases {
return TrackerUseCases(
trackFood = TrackFood(repository),
searchFood = SearchFood(repository),
getFoodsForDate = GetFoodsForDate(repository),
deleteTrackedFood = DeleteTrackedFood(repository),
calculateMealNutrients = CalculateMealNutrients(preferences)
)
}
}
 */
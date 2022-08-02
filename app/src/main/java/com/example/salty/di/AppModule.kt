package com.example.salty.di

import android.content.Context
import androidx.room.Room
import com.example.salty.data.local.AlbumDatabase
import com.example.salty.data.local.AlbumEntityDao
import com.example.salty.data.remote.api.ApiService
import com.example.salty.data.repository.AlbumRepositoryImplementation
import com.example.salty.data.repository.SearchRepositoryImplementation
import com.example.salty.domain.repository.AlbumRepository
import com.example.salty.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesProductDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AlbumDatabase::class.java, "album_database")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun providesProductDao(
        database: AlbumDatabase
    ) = database.albumDao()

    @Singleton
    @Provides
    fun albumRepository(
        apiService: ApiService,
        dao: AlbumEntityDao
    ) = AlbumRepositoryImplementation(
        api = apiService
    ) as AlbumRepository


    @Singleton
    @Provides
    fun searchRepository(
        apiService: ApiService,
        dao: AlbumEntityDao
    ) = SearchRepositoryImplementation(
        api = apiService,
        dao =  dao
    ) as SearchRepository

}

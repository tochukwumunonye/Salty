package com.example.salty.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salty.domain.model.Album
import com.example.salty.domain.usecase.AlbumUseCase
import com.example.salty.domain.usecase.GetAlbumUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.salty.presentation.albums.AlbumViewState.Loading
import com.example.salty.presentation.albums.AlbumViewState.Success
import com.example.salty.presentation.albums.AlbumViewState.Error
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class AlbumViewModel @Inject constructor(
   // private val albumUsecase: AlbumUseCase
      private val getAlbumUsecase: GetAlbumUsecase
) : ViewModel() {

    private var _viewState = MutableStateFlow<AlbumViewState>(Success(emptyList()))
    val viewState: StateFlow<AlbumViewState> = _viewState

    init{
        getAlbums()
    }

    fun getAlbums() {
        _viewState.value = Loading

        viewModelScope.launch {
            getAlbumUsecase.invoke()
                .catch { exception ->
                   _viewState.value = Error( exception.localizedMessage ?: "An error occurred")
                }
                .collect {
                    _viewState.value = Success(it)
                }
          /**
           class LatestNewsViewModel(
           private val newsRepository: NewsRepository
           ) : ViewModel() {

           init {
           viewModelScope.launch {
           newsRepository.favoriteLatestNews
           // Intermediate catch operator. If an exception is thrown,
           // catch and update the UI
           .catch { exception -> notifyError(exception) }
           .collect { favoriteNews ->
           // Update View with the latest favorite news
           }
           }
           }
           }**/
        }
    }
}


sealed class AlbumViewState {
    object Loading : AlbumViewState()
    data class Error(val error: String) : AlbumViewState()
    data class Success(val albums: List<Album>) : AlbumViewState()
}
/**
fun insertProductInDatabase(product: Product) = viewModelScope.launch {
repository.insertIntoDatabase(product)
}
}











}**/
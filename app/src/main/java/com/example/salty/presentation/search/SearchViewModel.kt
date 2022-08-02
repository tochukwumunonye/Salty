package com.example.salty.presentation.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.usecase.AlbumUseCase
import com.example.salty.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.salty.presentation.search.SearchViewState.Error
import com.example.salty.presentation.search.SearchViewState.Loading
import com.example.salty.presentation.search.SearchViewState.Success
import kotlinx.coroutines.flow.*

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val albumUseCase: AlbumUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<SearchViewState>(Success(emptyList()))
    val viewState: StateFlow<SearchViewState> = _viewState


    fun search(query: String) {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                albumUseCase.searchUseCase.invoke(query)
            }.onFailure { error ->
                _viewState.value = Error(error.localizedMessage ?: "An error occurred")
            }.onSuccess { searchedAlbums->
                Log.d("horny", searchedAlbums.toString())
                _viewState.value = Success(searchedAlbums)
            }
        }
    }

    fun save(album: SearchAlbum) = viewModelScope.launch {
        albumUseCase.saveAlbum(album)
    }
}

sealed class SearchViewState {
    object Loading : SearchViewState()
    data class Error(val error: String) : SearchViewState()
    data class Success(val albums: List<SearchAlbum>) : SearchViewState()
}

/**


fun insertProductInDatabase(product: Product) = viewModelScope.launch {
repository.insertIntoDatabase(product)
}
}**/

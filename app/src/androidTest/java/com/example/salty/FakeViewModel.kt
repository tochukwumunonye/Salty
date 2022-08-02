package com.example.salty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.usecase.AlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FakeViewModel  @Inject constructor(
    private val albumUseCase: AlbumUseCase
) : ViewModel() {


    private var _viewState = MutableStateFlow<FakeViewState>(FakeViewState.Success(emptyList()))
    val viewState: StateFlow<FakeViewState> = _viewState


    fun save(album: SearchAlbum) = viewModelScope.launch {
        albumUseCase.saveAlbum(album)
    }
}

sealed class FakeViewState {
    object Loading : FakeViewState()
    data class Error(val error: String) : FakeViewState()
    data class Success(val albums: List<SearchAlbum>) : FakeViewState()
}


/**
 *
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





 */
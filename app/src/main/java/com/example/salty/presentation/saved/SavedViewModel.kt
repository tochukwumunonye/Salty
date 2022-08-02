package com.example.salty.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.usecase.AlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.salty.presentation.saved.SavedViewState.Error
import com.example.salty.presentation.saved.SavedViewState.Success
import com.example.salty.presentation.search.SearchViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val  albumUseCase: AlbumUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<SavedViewState>(Success(emptyList()))
    val viewState: StateFlow<SavedViewState> = _viewState

    init {
        getSavedAlbums()
    }

    private fun getSavedAlbums() {
       viewModelScope.launch {
           albumUseCase.savedAlbum.invoke()
               .collect {
                   _viewState.value = Success(it)
               }
       }
    }


    fun deleteDateFromDatabase(album: SearchAlbum) = viewModelScope.launch {
        albumUseCase.deleteAlbum(album)
    }
}

sealed class SavedViewState {
    data class Error(val error: String) : SavedViewState()
    data class Success(val albums: List<SearchAlbum>) : SavedViewState()
}

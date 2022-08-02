package com.example.salty.domain.usecase

data class AlbumUseCase(
    val searchUseCase: SearchUseCase,
    val saveAlbum: SaveAlbumUseCase,
    val savedAlbum: SavedAlbumUseCase,
    val deleteAlbum: DeleteAlbumUseCase
) {
}

/**
 *
private fun executeSearch() {
viewModelScope.launch {
state = state.copy(
isSearching = true,
trackableFood = emptyList()
)
trackerUseCases
.searchFood(state.query)
.onSuccess { foods ->
state = state.copy(
trackableFood = foods.map {
TrackableFoodUiState(it)
},
isSearching = false,
query = ""
)
}
.onFailure {
state = state.copy(isSearching = false)
_uiEvent.send(
UiEvent.ShowSnackbar(
UiText.StringResource(R.string.error_something_went_wrong)
)
)
}
}
}
 */
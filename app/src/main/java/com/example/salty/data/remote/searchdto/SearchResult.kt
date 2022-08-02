package com.example.salty.data.remote.searchdto

import com.google.gson.annotations.SerializedName


data class SearchResult(
    @SerializedName("results")
    val results: List<SearchDto>
)
package com.example.salty.data.remote.api

import com.example.salty.data.remote.searchdto.SearchResult
import com.example.salty.data.remote.topalbumsdto.TopAlbumsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums() : TopAlbumsResult

    @GET("/search")
    suspend fun search(
        @Query("term") word: String,
        @Query("limit") limit: Int = 10
    ): SearchResult

    companion object {
        const val BASE_URL = "https://itunes.apple.com"
    }
}

/**
 *
 *
 * https://itunes.apple.com/search?term=jack+johnson&limit=25
 *
interface ApiService {

@GET("/sites/MLU/search/")
suspend fun getProducts(
@Query("q") word: String,
): Catalogue

@GET("/items/{id}/")
suspend fun getProductDetails(
@Path("id") id: String,
): CatalogueItem

companion object{
const val BASE_URL = "https://api.mercadolibre.com/"
}
}

https://itunes.apple.com/us/rss/topalbums/limit=100/json**/
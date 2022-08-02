package com.example.salty.data.repository

import android.util.Log
import com.example.salty.data.local.AlbumEntityDao
import com.example.salty.data.local.entity.toSearchAlbum
import com.example.salty.data.remote.api.ApiService
import com.example.salty.data.remote.searchdto.toSearchAlbum
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.model.toAlbumEntity
import com.example.salty.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImplementation @Inject constructor(
    private val api: ApiService,
    private val dao: AlbumEntityDao
): SearchRepository{

    override suspend fun search(query: String): List<SearchAlbum> {
        val kk = query
        val pp = api.search(query).results

        val tt = api.search(query).results.map{ it.toSearchAlbum()}
        Log.d("onyi", kk)
        Log.d("ada", pp.toString())

        return api.search(query).results.map{it.toSearchAlbum()}
    }

    override suspend fun insertIntoDatabase(album: SearchAlbum) {
        Log.d("hot", album.toAlbumEntity().toString())
        dao.insertAlbum(album.toAlbumEntity())
    }

    override suspend fun getSearchedAlbums(): Flow<List<SearchAlbum>> {
       return dao.returnAlLSavedAlbums().map {it.map { it.toSearchAlbum() } }
    }

    override suspend fun deleteAlbum(album: SearchAlbum) {
        val mhh = album
        Log.d("how", mhh.toString())
        dao.deleteAlbum(album.toAlbumEntity())
    }
}

/**
 *
 *
override suspend fun insertIntoDatabase(album: Album) {
dao.insertAlbum(album.toAlbumEntity())
}
 *
 *
 *
class ProductRepositoryImplementation @Inject constructor(
private val api: ApiService,
private val dao: ProductDao
): ProductRepositoryDomain{

override suspend fun searchProduct(query: String): List<Product> {
return api.getProducts(query).results.map{it.toProduct()}
}

override suspend fun getProductDetails(word: String): ProductItem {
return api.getProductDetails(word).toProductItem()
}

override suspend fun insertIntoDatabase(product: Product) {
dao.insertProduct(product.toProductEntity())
}

override suspend fun retrieveProductsFromDatabase(): List<Product> {
return dao.returnLastProducts().map{it.toProduct()}
}
}

 */
package com.example.salty.data.repository

import com.example.salty.data.local.AlbumEntityDao
import com.example.salty.data.remote.api.ApiService
import com.example.salty.data.remote.topalbumsdto.Entry
import com.example.salty.data.remote.topalbumsdto.toAlbum
import com.example.salty.domain.model.Album
import com.example.salty.domain.model.toAlbumEntity
import com.example.salty.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImplementation  @Inject constructor(
    private val api: ApiService
) : AlbumRepository {

    override suspend fun getAlbums(): Flow<List<Album>> = flow {
        val ll = api.getTopAlbums().feed.entry.map{it.toAlbum()}
        emit(ll)
    }
}

/**
 *
override suspend fun insertTrackedFood(food: TrackedFood) {
dao.insertTrackedFood(food.toTrackedFoodEntity())
 *
 *  fun getArticle(id: String) = flow {
val article = database.articleDao().getNewsById(id)
emit(article)
}.flowOn(Dispatchers.Default)
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
}**/
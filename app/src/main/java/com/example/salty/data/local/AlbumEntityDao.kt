package com.example.salty.data.local

import androidx.room.*
import com.example.salty.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumEntityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)

    @Query("SELECT * FROM album_table")
    fun returnAlLSavedAlbums(): Flow<List<AlbumEntity>>
}



/**
 *
@Dao
interface ProductDao {

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insertProduct(product: ProductEntity)

@Query("SELECT * FROM product_table ORDER BY PK DESC LIMIT 5")
fun returnLastProducts() : List<ProductEntity>

}
------------------------------------------------------
@Update(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertCollection(collection: CollectionEntity)


@Insert(onConflict =  OnConflictStrategy.IGNORE)
suspend fun insertCollectionList(collection: List<CollectionEntity>)


@Query("SELECT * FROM collection_items")
fun returnAllCollections() : List<CollectionEntity>



@Query("DELETE FROM collection_items")
suspend fun clearNews()**/
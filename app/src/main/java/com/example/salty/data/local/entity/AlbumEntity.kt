package com.example.salty.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.salty.domain.model.SearchAlbum

@Entity(tableName = "album_table")
class AlbumEntity(
    @ColumnInfo(name = "artist")
    val artist: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @PrimaryKey(autoGenerate =  true)
    val id: Int? = null
) {
}


fun AlbumEntity.toSearchAlbum() : SearchAlbum {
    return SearchAlbum(
        artistName = artist,
        artworkUrl = image,
        trackName = name,
    )
}
/**


@Entity(tableName = "product_table")
class ProductEntity(
@ColumnInfo(name = "id")
val id: String,
@ColumnInfo(name = "price")
val price: Double,
@ColumnInfo(name = "thumbnail")
val thumbnail: String,
@ColumnInfo(name = "title")
val title: String,
@PrimaryKey(autoGenerate =  true)
val pk: Int = 0,
)


fun ProductEntity.toProduct() : Product {
return Product(
id = id,
price = price,
thumbnail = thumbnail,
title = title,
)
}
 **/

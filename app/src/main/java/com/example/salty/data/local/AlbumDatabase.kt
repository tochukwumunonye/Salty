package com.example.salty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.salty.data.local.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 3, exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumEntityDao
}
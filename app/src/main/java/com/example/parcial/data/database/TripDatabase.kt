package com.example.parcial.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parcial.data.database.dao.FavouriteDao
import com.example.parcial.data.database.entities.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1,  exportSchema = false)
abstract class TripDatabase: RoomDatabase() {
    abstract fun getFavouriteDao(): FavouriteDao

}
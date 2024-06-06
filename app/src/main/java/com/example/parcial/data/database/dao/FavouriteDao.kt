package com.example.parcial.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial.data.database.entities.FavouriteEntity

@Dao
interface FavouriteDao {
    @Query("SELECT EXISTS(SELECT * FROM favourites WHERE type = :type AND type_id = :id)")
    suspend fun existsFavouriteByTypeAndId(type: String, id: String): Boolean

    @Query("SELECT * FROM favourites WHERE type = :type")
    suspend fun getAllFavourites(type: String): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteEntity): Long

    @Delete(entity = FavouriteEntity::class)
    suspend fun deleteFavourite(favourite: FavouriteEntity): Int
}
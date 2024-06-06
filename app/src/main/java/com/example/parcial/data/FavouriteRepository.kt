package com.example.parcial.data

import com.example.parcial.data.database.dao.FavouriteDao
import com.example.parcial.data.database.entities.FavouriteEntity
import com.example.parcial.data.database.entities.toDatabase
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import com.example.parcial.entities.toDomain
import javax.inject.Inject

class FavouriteRepository @Inject constructor(
    private val favouriteDao: FavouriteDao
) {
    suspend fun existsFavourite(favourite: Favourite): Boolean {
        return favouriteDao.existsFavouriteByTypeAndId(favourite.type, favourite.typeId)
    }
    suspend fun getFavouritesDestinationsFromDatabase():List<Favourite>{
        val response: List<FavouriteEntity> = favouriteDao.getAllFavourites(FavouriteType.DESTINATION.type)
        return response.map { it.toDomain() }
    }

    suspend fun createFavourite(favourite: Favourite): Long {
        return favouriteDao.insertFavourite(favourite.toDatabase())
    }

    suspend fun deleteFavourite(favourite: Favourite): Int {
        return favouriteDao.deleteFavourite(favourite.toDatabase())
    }

    suspend fun getFavouritesOffersFromDatabase(): List<Favourite>? {
        val response: List<FavouriteEntity> = favouriteDao.getAllFavourites(FavouriteType.OFFER.type)
        return response.map { it.toDomain() }
    }
}
package com.example.parcial.domain

import com.example.parcial.data.FavouriteRepository
import com.example.parcial.entities.Favourite
import javax.inject.Inject

class FavouriteUseCase @Inject constructor(private val repository: FavouriteRepository) {

        suspend fun exists(favourite: Favourite): Boolean {
            return repository.existsFavourite(favourite)
        }

        suspend fun getFavouritesDestinations(): List<Favourite>? {
                val favourites = repository.getFavouritesDestinationsFromDatabase()
                if (!favourites.isNullOrEmpty()) {
                        return favourites
                }
                return null
        }

        suspend fun getFavouritesOffers(): List<Favourite>? {
                val favourites = repository.getFavouritesOffersFromDatabase()
                if (!favourites.isNullOrEmpty()) {
                        return favourites
                }
                return null
        }

        suspend fun saveFavourite(favourite: Favourite): Long {
               return repository.createFavourite(favourite)
        }

        suspend fun removeFavourite(favourite: Favourite): Int {
                return repository.deleteFavourite(favourite)
        }
}
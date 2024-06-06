package com.example.parcial.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial.domain.FavouriteUseCase
import com.example.parcial.entities.Destination
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import com.example.parcial.entities.Offer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = OffersViewModel.OffersViewModelFactory::class)
class OffersViewModel @AssistedInject constructor(
    @Assisted val offers: List<Offer>,
    private val favouriteUseCase: FavouriteUseCase
): ViewModel() {

    @AssistedFactory
    interface OffersViewModelFactory {
        fun create(id: List<Offer>): OffersViewModel
    }

    init {
        areChecked()
    }

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    val favourites = MutableLiveData<List<Favourite>>()

    fun handleCheckboxStateChange(position: Int, isChecked: Boolean) {
        val offer = offers[position]

        if (isChecked) {
            addFavourite("${offer.discount}-${offer.brand}")
        } else {
            removeFavourite("${offer.discount}-${offer.brand}")
        }
    }
    private fun addFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.saveFavourite(Favourite(FavouriteType.OFFER.type, id))

            if (result != null) {
                isSaved.postValue(result != -1L)
            }

        }
    }

    private fun removeFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.removeFavourite(Favourite(FavouriteType.OFFER.type, id))
            if (result != null) {
                isDeleted.postValue(result != -1)
            }
        }
    }

    private fun areChecked() {
        CoroutineScope(Dispatchers.Main).launch {
            val favouritesOffers = favouriteUseCase.getFavouritesOffers()

            if (favouritesOffers != null) {
                favourites.postValue(favouritesOffers!!)
            }else{
                favourites.postValue(listOf())
            }
        }
    }

}
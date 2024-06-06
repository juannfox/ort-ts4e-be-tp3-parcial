package com.example.parcial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial.domain.FavouriteUseCase
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase
): ViewModel(){

    private val MAIN_DESTINATION = "Paris-Francia"

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    val isFavourite = MutableLiveData<Boolean>()

    init {
        isChecked(MAIN_DESTINATION)
    }
    fun addFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.saveFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isSaved.postValue(result != -1L)
            }

        }
    }
    fun removeFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.removeFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isDeleted.postValue(result != -1)
            }
        }
    }

    fun isChecked(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result = favouriteUseCase.exists(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isFavourite.postValue(result)
            }
        }
    }
}
package com.example.parcial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial.domain.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.parcial.entities.Destination
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DestinationDetailViewModel.DestinationDetailViewModelFactory::class)
class DestinationDetailViewModel @AssistedInject constructor(
    @Assisted val destination: Destination,
    private val favouriteUseCase: FavouriteUseCase
): ViewModel() {

    @AssistedFactory
    interface DestinationDetailViewModelFactory {
        fun create(id: Destination): DestinationDetailViewModel
    }

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val isFavourite = MutableLiveData<Boolean>()

    init {
        isChecked("${destination.destinationName}-${destination.city}")
    }

    public fun addFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.saveFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isSaved.postValue(result != -1L)
            }

        }
    }

    public fun removeFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.removeFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isDeleted.postValue(result != -1)
            }
        }
    }

    public fun isChecked(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.postValue(true)
            var result = favouriteUseCase.exists(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isFavourite.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}
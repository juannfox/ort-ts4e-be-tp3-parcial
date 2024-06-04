package com.example.parcial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial.domain.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.parcial.entities.Destination
import kotlinx.coroutines.launch

@HiltViewModel
class DestinationDetailViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase
): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isFavourite = MutableLiveData<Boolean>()

    lateinit var destination: Destination
    /*fun onCreate() {

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = favouriteUseCase.exists()

            if (result.isNotEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun setDestination(destination: Destination) {
        this.destination = destination
    }*/
}
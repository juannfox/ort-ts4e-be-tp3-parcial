package com.example.parcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.adapters.OfferAdapter
import com.example.parcial.databinding.FragmentOffersBinding
import com.example.parcial.domain.FavouriteUseCase
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import com.example.parcial.entities.Offer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OffersFragment : Fragment() {

    private lateinit var binding: FragmentOffersBinding

    @Inject
    lateinit var favouriteUseCase: FavouriteUseCase

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    //val isLoading = MutableLiveData<Boolean>()
    //val isFavourite = MutableLiveData<Boolean>()

    lateinit var recOffers: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var offerAdapter: RecyclerView.Adapter<*>

    private var offers: MutableList<Offer> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //vista = inflater.inflate(R.layout.fragment_offers, container, false)
        binding = FragmentOffersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        fillList()

        recOffers = binding.recOffers
        recOffers.setHasFixedSize(true)
        manager = LinearLayoutManager(context)
        offerAdapter = OfferAdapter(offers) { position, isChecked ->
            // Handle checkbox state change
            handleCheckboxStateChange(position, isChecked)
        }

        recOffers.layoutManager = manager
        recOffers.adapter = offerAdapter

        /*isFavourite.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                binding.destinationLikeButton.isChecked = true
            }
        })*/


        isSaved.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_saved_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        isDeleted.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_deleted_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun fillList() {
        var offer1 = Offer(
            "LIMITED OFFER!",
            "Get 20% discount with your Master credit cards",
            "Use your mastercard with any transaction and get 20% discount instantly!",
            "https://i.ibb.co/MCM7tr6/offer-fav.png",
            "https://i.ibb.co/QvrfXLH/offer-cardm.png",
            "https://i.ibb.co/syTxRWH/offer-vector.png",
            20,
            "Mastercard"
        )

        var offer2 = Offer(
            "LIMITED OFFER!",
            "Get 15% discount with your Visa Credit or debit cards",
            "Use your VISA credit or debit card with any transaction and get 20% discount instantly!",
            "https://i.ibb.co/MCM7tr6/offer-fav.png",
            "https://i.ibb.co/zmKtCtq/offer-cardv.png",
            "https://i.ibb.co/syTxRWH/offer-vector.png",
            15,
            "VISA"
        )

        offers.add(offer1)
        offers.add(offer2)
    }

    private fun handleCheckboxStateChange(position: Int, isChecked: Boolean) {
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
                favouriteUseCase.saveFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isSaved.postValue(result != -1L)
            }

        }
    }

    private fun removeFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.removeFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isDeleted.postValue(result != -1)
            }
        }
    }

    /* private fun isChecked(id: String) {
         CoroutineScope(Dispatchers.Main).launch {
             isLoading.postValue(true)
             var result = favouriteUseCase.exists(Favourite(FavouriteType.OFFER.type, id))

             if (result != null) {
                 isFavourite.postValue(result)
                 isLoading.postValue(false)
             }
         }
     }*/
}
package com.example.parcial.fragments

import android.os.Bundle
import android.util.Log
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

    @Inject
    lateinit var favouriteUseCase: FavouriteUseCase

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    val favourites = MutableLiveData<List<Favourite>>()

    lateinit var offerView: View
    lateinit var recOffers: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var offerAdapter: RecyclerView.Adapter<*>
    lateinit private var binding: FragmentOffersBinding

    private var offers: MutableList<Offer> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersBinding.inflate(layoutInflater)
        offerView = binding.root
        areChecked()
        return offerView
    }

    override fun onStart() {
        super.onStart()
        fillList()

        recOffers = binding.recOffers
        recOffers.setHasFixedSize(true)
        manager = LinearLayoutManager(context)
        recOffers.layoutManager = manager


        favourites.observe(viewLifecycleOwner, Observer<List<Favourite>> {
            offerAdapter = OfferAdapter(offers, it?: listOf()) { position, isChecked ->
                handleCheckboxStateChange(position, isChecked)
            }
            recOffers.adapter = offerAdapter
        })

        isSaved.observe(viewLifecycleOwner, Observer<Boolean> { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_offer_saved_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })


        isDeleted.observe(viewLifecycleOwner, Observer { isDeletedValue ->
            if (isDeletedValue) {
                Snackbar.make(binding.root, R.string.favourite_offer_deleted_alert, Snackbar.LENGTH_SHORT)
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
                Log.d("PARCIAL-FAVS", result.toString())
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
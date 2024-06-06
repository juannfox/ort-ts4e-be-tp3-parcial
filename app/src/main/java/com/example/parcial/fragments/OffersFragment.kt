package com.example.parcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.adapters.OfferAdapter
import com.example.parcial.databinding.FragmentOffersBinding
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.Offer
import com.example.parcial.viewmodel.OffersViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class OffersFragment : Fragment() {

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
        return offerView
    }

    override fun onStart() {
        super.onStart()
        fillList()
        val viewModel by viewModels<OffersViewModel>(
            extrasProducer = {
                defaultViewModelCreationExtras.withCreationCallback<
                        OffersViewModel.OffersViewModelFactory> { factory ->
                    factory.create(offers)
                }
            }
        )

        recOffers = binding.recOffers
        recOffers.setHasFixedSize(true)
        manager = LinearLayoutManager(context)


        //offerAdapter = OfferAdapter(offers) //,true)

        recOffers.layoutManager = manager


        viewModel.favourites.observe(viewLifecycleOwner, Observer<List<Favourite>> {
            offerAdapter = OfferAdapter(offers, it ?: listOf()) { position, isChecked ->
                viewModel.handleCheckboxStateChange(position, isChecked)
            }
            recOffers.adapter = offerAdapter
        })

        /*viewModel.isSaved.observe(viewLifecycleOwner, Observer<Boolean> { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(
                    binding.root,
                    R.string.favourite_offer_saved_alert,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        viewModel.isDeleted.observe(viewLifecycleOwner, Observer { isDeletedValue ->
            if (isDeletedValue) {
                Snackbar.make(
                    binding.root,
                    R.string.favourite_offer_deleted_alert,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })*/
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
}
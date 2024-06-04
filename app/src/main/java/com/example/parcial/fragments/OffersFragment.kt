package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.adapters.OfferAdapter
import com.example.parcial.entities.Offer

class OffersFragment : Fragment() {

    lateinit var vista: View
    lateinit var recOffers: RecyclerView
    lateinit var manager : RecyclerView.LayoutManager
    lateinit var offerAdapter: RecyclerView.Adapter<*>

    private var offers: MutableList<Offer> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_offers, container, false)

        return vista
    }

    override fun onStart() {
        super.onStart()

        fillList()

        recOffers = vista.findViewById(R.id.rec_offers)
        recOffers.setHasFixedSize(true)
        manager = LinearLayoutManager(context)
        offerAdapter = OfferAdapter(offers)

        recOffers.layoutManager = manager
        recOffers.adapter = offerAdapter


    }

   fun fillList(){
        var offer1 = Offer(
            "LIMITED OFFER!",
            "Get 20% discount with your Master credit cards",
            "Use your mastercard with any transaction and get 20% discount instantly!",
            "https://i.ibb.co/MCM7tr6/offer-fav.png",
            "https://i.ibb.co/QvrfXLH/offer-cardm.png",
            "https://i.ibb.co/syTxRWH/offer-vector.png"
        )

        var offer2 = Offer(
            "LIMITED OFFER!",
            "Get 15% discount with your Visa Credit or debit cards",
            "Use your VISA credit or debit card with any transaction and get 20% discount instantly!",
            "https://i.ibb.co/MCM7tr6/offer-fav.png",
            "https://i.ibb.co/zmKtCtq/offer-cardv.png",
            "https://i.ibb.co/syTxRWH/offer-vector.png"
        )

        offers.add(offer1)
        offers.add(offer2)
    }
}
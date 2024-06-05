package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Offer
import com.example.parcial.holders.OtherOffersHolder

class OtherOffersAdapter(
    private var offers: MutableList<Offer>,
):RecyclerView.Adapter<OtherOffersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherOffersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_other_offers, parent, false)
        return (OtherOffersHolder(view))
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: OtherOffersHolder, position: Int) {
        val offer = offers[position]

        holder.setOtherImage(offer.image_card)
        holder.setOtherTitle(offer.text_tittle)
        holder.setOtherDescription(offer.text_description)

        //holder.setlimOffer(offer.limited_offer)
        //holder.setTitle(offer.text_tittle)
        //holder.setDescription(offer.text_description)
        //holder.setCardImage(offer.image_card)
        //holder.setFavImage(offer.image_fav)
        //holder.setVectorImage(offer.image_vector)
    }
}
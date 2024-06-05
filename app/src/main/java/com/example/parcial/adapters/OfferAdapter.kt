package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Offer
import com.example.parcial.holders.OfferHolder

class OfferAdapter(
    private var offers: MutableList<Offer>,
   // private var ShowVectorFav: Boolean,
): RecyclerView.Adapter<OfferHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.offer_card, parent, false)
        return (OfferHolder(view))
    }

    override fun getItemCount() = offers.size

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {

        val offer = offers[position]

        holder.setlimOffer(offer.limited_offer)
        holder.setTitle(offer.text_tittle)
        holder.setDescription(offer.text_description)
        holder.setCardImage(offer.image_card)

       // if(ShowVectorFav){
            holder.setFavImage(offer.image_fav)
            holder.setVectorImage(offer.image_vector)
        //}else{
          //  holder.hideVectorFav()
        //}

    }

}
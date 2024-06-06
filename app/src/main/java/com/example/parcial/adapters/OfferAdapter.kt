package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Destination
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.Offer
import com.example.parcial.holders.OfferHolder
import com.example.parcial.listeners.OnViewItemClickedListener

class OfferAdapter(
    private var offers: MutableList<Offer>,
    private val favoriteIds: List<Favourite>,
    private val checkBoxChangeListener: (Int, Boolean) -> Unit
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
        holder.setVectorImage(offer.image_vector)

        val checkBox: CheckBox = holder.getOfferLikeButton() as CheckBox
        checkBox.isChecked = favoriteIds.map{  it.typeId  }.contains("${offer.discount}-${offer.brand}")

        (holder.getOfferLikeButton() as CheckBox).setOnCheckedChangeListener { _, isChecked ->
                checkBoxChangeListener(position, isChecked)
        }
    }

}
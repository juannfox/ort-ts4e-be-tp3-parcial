package com.example.parcial.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.bumptech.glide.Glide

class OfferHolder(v: View) : RecyclerView.ViewHolder(v) {

    private var view: View
    init {
        view = v
    }

    fun setlimOffer(limOffer: String?){
        val txt = view.findViewById<TextView>(R.id.limited_offer)
        txt.text = limOffer
    }

    fun setTitle(title: String?){
        val txt = view.findViewById<TextView>(R.id.text_tittle)
        txt.text = title
    }

    fun setDescription(description: String?){
        val txt = view.findViewById<TextView>(R.id.text_description)
        txt.text = description
    }

    fun setCardImage(url: String?){
        val img : ImageView = view.findViewById(R.id.image_card)
        Glide.with(view).load(url).into(img)
    }

    fun setFavImage(url: String?){
        val img : ImageView = view.findViewById(R.id.image_fav)
        Glide.with(view).load(url).into(img)
    }

    fun setVectorImage(url: String?){
      val img : ImageView = view.findViewById(R.id.image_vector)
       Glide.with(view).load(url).into(img)
    }

    fun getCardOfferView(): View {
        return view.findViewById(R.id.offer_card)
    }

}
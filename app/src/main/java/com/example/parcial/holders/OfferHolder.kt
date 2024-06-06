package com.example.parcial.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.R
import com.example.parcial.databinding.OfferCardBinding

class OfferHolder(v: View) : RecyclerView.ViewHolder(v) {

    private var view: View = v;
    private val binding = OfferCardBinding.bind(v)

    fun setlimOffer(limOffer: String?){
        binding.limitedOffer.text = limOffer
    }

    fun setTitle(title: String?){
        binding.textTittle.text = title
    }

    fun setDescription(description: String?){
        binding.textDescription.text = description
    }

    fun setCardImage(url: String?){
        Glide.with(view).load(url).into(binding.imageCard )
    }


    /*
    fun setFavImage(url: String?){
        Glide.with(view).load(url).into(binding.imageFav)
    }
    */
    fun setVectorImage(url: String?){
       Glide.with(view).load(url).override(70, 70).into(binding.imageVector)
    }

    fun getOfferLikeButton(): View {
        return view.findViewById(R.id.offer_like_button)
    }

   // fun hideVectorFav(){
     //   binding.imageFav.visibility = View.GONE
       // binding.imageVector.visibility = View.GONE
    //}

    //fun getCardOfferView(): View {
      //  return view.findViewById(R.id.offer_card)
    //}

}
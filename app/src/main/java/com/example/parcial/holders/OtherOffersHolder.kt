package com.example.parcial.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.databinding.CardOtherOffersBinding

class OtherOffersHolder(v: View): RecyclerView.ViewHolder(v){

    private var view: View = v;
    private val binding = CardOtherOffersBinding.bind(v)

   fun setOtherImage(url : String?){
       Glide.with(view).load(url).override(300, 300).into(binding.otherImageCard)
   }

    fun setOtherTitle(title: String?){
        binding.otherTextTittle.text = title
    }

    fun setOtherDescription(description: String?){
        binding.otherTextDescription.text = description
    }

    //fun getOtherCardOfferView(): View {
      //  return binding.root
    //}
}


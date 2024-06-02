package com.example.parcial.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.databinding.CardDestinationItemBinding
import com.example.parcial.databinding.FlightCardDetailBinding

class DestinationHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view : View = v;
    private val binding = CardDestinationItemBinding.bind(view);

    fun setDestinationName(destinationName: String){
        binding.destinationName.text = destinationName
    }

    fun setCity(city: String){
        binding.destinationCity.text = city
    }

    fun setDuration(duration: String){
        binding.tripDuration.text = duration
    }

    fun setImage (url: String?){
        Glide.with(view).load(url).into(binding.imageDestination)
    }

    fun getCardLayout () : View {
        return binding.destinationCard
    }
}
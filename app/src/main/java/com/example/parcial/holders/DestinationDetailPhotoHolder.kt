package com.example.parcial.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.databinding.DestinationDetailCardPhotoBinding

class DestinationDetailPhotoHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v;
    private val binding = DestinationDetailCardPhotoBinding.bind(view);

    fun setImage(url: String?) {
        Glide.with(view).load(url).into(binding.imageDestination)
    }
}
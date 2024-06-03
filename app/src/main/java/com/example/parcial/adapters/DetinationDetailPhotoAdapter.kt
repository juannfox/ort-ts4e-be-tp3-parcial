package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.holders.DestinationDetailPhotoHolder

class DetinationDetailPhotoAdapter(
    private val destinationPhotos: MutableList<String>
) : RecyclerView.Adapter<DestinationDetailPhotoHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DestinationDetailPhotoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.destination_detail_card_photo, parent, false)
        return DestinationDetailPhotoHolder(view)
    }

    override fun getItemCount(): Int {
        return destinationPhotos.size
    }

    override fun onBindViewHolder(holder: DestinationDetailPhotoHolder, position: Int) {
        val photo = destinationPhotos[position]
        holder.setImage(photo)
    }

}
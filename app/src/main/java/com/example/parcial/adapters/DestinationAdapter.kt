package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Destination
import com.example.parcial.holders.DestinationHolder
import com.example.parcial.listeners.OnViewItemClickedListener

class DestinationAdapter(
    private val destinations: MutableList<Destination>,
    private val onItemClick: OnViewItemClickedListener<Destination>
) : RecyclerView.Adapter<DestinationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_destination_item, parent, false)
        return DestinationHolder(view)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    override fun onBindViewHolder(holder: DestinationHolder, position: Int) {
        val destination = destinations[position]

        holder.setDestinationName(destination.destinationName)
        holder.setCity(destination.city)
        holder.setDuration(destination.duration)
        holder.setImage(destination.images[0])

        holder.getCardLayout().setOnClickListener {
            onItemClick.onViewItemDetail(destination)
        }
    }

}
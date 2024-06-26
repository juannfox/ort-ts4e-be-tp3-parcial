package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Trip
import com.example.parcial.holders.TripHolder
import com.example.parcial.listeners.OnViewItemClickedListener

class TripAdapter(
    private val trips: MutableList<Trip>,
    private val onItemClick: OnViewItemClickedListener<Trip>

): RecyclerView.Adapter<TripHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_card_detail, parent, false)
        return TripHolder(view)
    }

    override fun getItemCount(): Int {
        return  trips.size;
    }

    override fun onBindViewHolder(holder: TripHolder, position: Int) {
        val trip = trips[position]

        holder.setDepartureAirportId(trip.departureAirport?.id!!)
        holder.setDepartureAirportName(trip.departureAirport?.name!!)
        holder.setArrivalAirportId(trip.arrivalAirport?.id!!)
        holder.setArrivalAirportName(trip.arrivalAirport?.name!!)
        holder.setDuration(trip.duration)
        holder.setAirline(trip.airline!!)
        holder.setAirlineLogo(trip.airlineLogo)
        holder.setTravelClass(trip.travelClass!!)
        holder.setPrice(trip.price)

        holder.getDetailButton().setOnClickListener {
            onItemClick.onViewItemDetail(trip)
        }


    }
}
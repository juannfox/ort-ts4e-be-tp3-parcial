package com.example.parcial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.entities.Flight
import com.example.parcial.holders.FlightHolder
import com.example.parcial.listeners.OnViewItemClickedListener

class FlightAdapter(private val flights : MutableList<Flight>,
                             private val onItemClick: OnViewItemClickedListener
): RecyclerView.Adapter<FlightHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_card_detail, parent, false)
        return FlightHolder(view)
    }

    override fun getItemCount(): Int {
        return  flights.size;
    }

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val flight = flights[position]

        holder.setDepartureAirportId(flight.departure_airport?.id!!)
        holder.setDepartureAirportName(flight.departure_airport?.name!!)
        holder.setArrivalAirportId(flight.arrival_airport?.id!!)
        holder.setArrivalAirportName(flight.arrival_airport?.name!!)
        holder.setDuration(flight.duration)
        holder.setAirlane(flight.airline!!)
        holder.setAirlineLogo(flight.airline_logo)
        holder.setTravelClass(flight.travel_class!!)

        holder.getCardLayout().setOnClickListener {
            onItemClick.onViewItemDetail(flight)
        }
    }
}
package com.example.parcial.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.databinding.FlightCardDetailBinding

class TripHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view : View = v;
    private val binding = FlightCardDetailBinding.bind(view)

    fun setDepartureAirportId(departureAirportId: String){
        binding.departureAirportId.text = departureAirportId
    }

    fun setDepartureAirportName(departureAirportName: String){
        binding.departureAirportName.text = formatAirportName(departureAirportName)
    }

    fun setArrivalAirportId(arrivalAirportId: String){
        binding.arrivalAirportId.text = arrivalAirportId;
    }

    fun setArrivalAirportName(arrivalAirportName: String){
        binding.departureAirportName.text = formatAirportName(arrivalAirportName)
    }
    fun setDuration(duration: Int){
        //Considerar que viene en minutos y debo separar horas y minutos
        val (hours, minutes) = extractHoursAndMinutes(duration)
        binding.duration.text = "${hours}hr ${minutes}min"
        //Ver de extraer texto de string resources
        //durationText.text = getString(R.string.flight_duration, 0,0);
    }

    fun setAirline(airlineName: String){
        binding.airlineName.text = airlineName
    }

    fun setAirlineLogo (url: String?){
        Glide.with(view).load(url).into(binding.airlineLogo)
    }

    fun setTravelClass(travelClass: String){
        binding.travelClass.text = travelClass
    }

    fun setPrice(price: Int){
        binding.price.text = price.toString()
    }

    fun getDetailButton () : View {
        return binding.tripDetailButton
    }

    //Revisar si corresponde mover esta lógica a otra clase
    private fun extractHoursAndMinutes(durationInMinutes: Int): Pair<Int, Int> {
        val hours = durationInMinutes / 60
        val minutes = durationInMinutes % 60
        return Pair(hours, minutes)
    }

    private fun formatAirportName(airportName: String): String {
        if(airportName.contains("International Airport")){
            return airportName.replace("International Airport", "Ar")
        } else if(airportName.contains("Intercontinental Airport")){
            return airportName.replace("Intercontinental Airport", "Ar")
        }
       return airportName
    }
}
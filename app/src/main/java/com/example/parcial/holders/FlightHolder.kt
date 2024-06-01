package com.example.parcial.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.R

class FlightHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view : View = v;

    fun setDepartureAirportId(departureAirportId: String){
        var departureAirportIdText: TextView = view.findViewById(R.id.departure_airport_id)
        departureAirportIdText.text = departureAirportId
    }

    fun setDepartureAirportName(departureAirportName: String){
        var departureAirportNameText: TextView = view.findViewById(R.id.departure_airport_name)
        departureAirportNameText.text = formatAirportName(departureAirportName)
    }

    fun setArrivalAirportId(arrivalAirportId: String){
        var arrivalAirportIdText: TextView = view.findViewById(R.id.arrival_airport_id)
        arrivalAirportIdText.text = arrivalAirportId;
    }

    fun setArrivalAirportName(arrivalAirportName: String){
        var arrivalAirportNameText: TextView = view.findViewById(R.id.arrival_airport_name)
        arrivalAirportNameText.text = formatAirportName(arrivalAirportName)
    }
    fun setDuration(duration: Int){
        //Considerar que viene en minutos y debo separar horas y minutos
        val (hours, minutes) = extractHoursAndMinutes(duration)
        val durationText : TextView = view.findViewById(R.id.duration)
        durationText.text = "${hours}hr ${minutes}min"
        //Ver de extraer texto de string resources
        //durationText.text = getString(R.string.flight_duration, 0,0);
    }

    fun setAirlane(airlaneName: String){
        val airlaneText : TextView =  view.findViewById(R.id.airlane_name)
        airlaneText.text = airlaneName
    }

    fun setAirlineLogo (url: String?){
        val img : ImageView = view.findViewById(R.id.airline_logo)
        Glide.with(view).load(url).into(img)
    }

    fun setTravelClass(travelClass: String){
        val travelClassText: TextView = view.findViewById(R.id.travel_class)
        travelClassText.text = travelClass
    }

    fun getCardLayout () : View {
        return view.findViewById(R.id.card_flight)
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
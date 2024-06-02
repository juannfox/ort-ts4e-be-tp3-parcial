package com.example.parcial.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.adapters.TripAdapter
import com.example.parcial.entities.Airport
import com.example.parcial.entities.Trip
import com.example.parcial.listeners.OnViewItemClickedListener
import com.example.parcial.models.APIResponse
import com.example.parcial.models.TripResponse
import com.example.parcial.services.APIClient
import com.example.parcial.services.FlightInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import com.example.parcial.databinding.FragmentSearchResultsBinding

class SearchResultsFragment: Fragment(), OnViewItemClickedListener<Trip> {

    lateinit var recyclerView: RecyclerView
    lateinit var tripAdapter: RecyclerView.Adapter<*>
    private lateinit var trips: MutableList<Trip>
    private lateinit var flightInterface: FlightInterface
    private lateinit var binding: FragmentSearchResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentSearchResultsBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()

        // Setup de Adapter
        trips = mutableListOf() // Inicialmente vacia
        tripAdapter = TripAdapter(trips, this@SearchResultsFragment)

        // Setup de Recycler view
        recyclerView = binding.rvTrips
        recyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = tripAdapter
            setHasFixedSize(true)
        }

        // Retrofit
        flightInterface = APIClient.create()
        // Cargar lista
        loadTrips()
    }

    private fun loadTrips(){
        /**
         * Carga la lista de vuelos desde la API
         */
        flightInterface.getAllTrips().enqueue(object: Callback<APIResponse> { // TODO: Usar co-rutinas directamente
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {

                if (response.isSuccessful) {
                    response.body()?.bestFlights?.forEach() { tripResponse ->
                        trips.add(mapTripResponse(tripResponse))
                    }
                    // Avisar asincronicamente que la lista esta disponible
                    tripAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("Error", t.stackTraceToString())
            }
        })
    }

    private fun mapTripResponse(tripResponse: TripResponse): Trip{
        /**
         * Mapea un objeto de tipo TripResponse (Model) a un objeto de tipo Trip (Entity)
         */
        // TODO: Cambiar logica para que se muestren las escalas!
        return Trip(
            Airport(
                tripResponse.flights.first().departure.id,
                tripResponse.flights.first().departure.name,
                tripResponse.flights.first().departure.time,
            ),
            Airport(
                tripResponse.flights.last().arrival.id,
                tripResponse.flights.last().arrival.name,
                tripResponse.flights.last().arrival.time,
            ),
            tripResponse.duration.toInt(),
            //tripResponse.duration as Int,
            tripResponse.flights.first().airplane,
            tripResponse.flights.first().airline,
            tripResponse.logo,
            tripResponse.flights.first().flightClass,
            tripResponse.flights.first().flightNumber,
            tripResponse.flights.first().legroom,
            tripResponse.flights.first().overnight,
            tripResponse.price.toInt()
        )
    }


    override fun onViewItemDetail(entity: com.example.parcial.entities.Trip) {
        //Intent a vista detalle
        Toast.makeText(context, "Item seleccionado: ${entity.departureAirport?.id} - ${entity.arrivalAirport?.id}", Toast.LENGTH_SHORT).show()
    }
}
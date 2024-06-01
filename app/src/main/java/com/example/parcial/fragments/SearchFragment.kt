package com.example.parcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.adapters.FlightAdapter
import com.example.parcial.entities.Airport
import com.example.parcial.entities.Flight
import com.example.parcial.listeners.OnViewItemClickedListener

class SearchFragment : Fragment(), OnViewItemClickedListener {

    lateinit var searchResultView: View

    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var dogAdapter: RecyclerView.Adapter<*>

    private var flights: MutableList<Flight> = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchResultView = inflater.inflate(R.layout.fragment_search, container, false)
        return  searchResultView
    }

    override fun onStart() {
        super.onStart()

        fillList()
        recyclerView = searchResultView.findViewById(R.id.flights_recycler)
        recyclerView.setHasFixedSize(true)

        manager = LinearLayoutManager(context)
        dogAdapter = FlightAdapter(flights, this)

        recyclerView.layoutManager = manager
        recyclerView.adapter = dogAdapter
    }

    fun fillList(){
        var depart1 = Airport("EZE","Ezeiza International Airport","2024-05-31 15:00")
        var arriv1 = Airport("JFK","John F. Kennedy International Airport","2024-05-31 15:00")
        var arriv2 = Airport("MIA","Miami International Airport","2024-05-31 15:00")
        var arriv3 = Airport("VVI","Viru Viru International Airport","2024-05-31 15:00")
        var arriv4 = Airport("IAH","George Bush Intercontinental Airport","2024-05-31 15:00")

        flights.add(Flight(depart1,arriv1,162,"","United","https://www.gstatic.com/flights/airline_logos/70px/UA.png","Economy","","",true))
        flights.add(Flight(depart1,arriv2,575,"","Aeromexico","https://www.gstatic.com/flights/airline_logos/70px/AM.png","Economy","","",false))
        flights.add((Flight(depart1,arriv3,231,"","Avianca","https://www.gstatic.com/flights/airline_logos/70px/AV.png","Bissiness Class","","",true)))
        flights.add(Flight(depart1,arriv4,651,"","American","https://www.gstatic.com/flights/airline_logos/70px/AA.png","Premium","","",false))

    }

    override fun onViewItemDetail(flight: Flight) {
        //Intent a vista detalle
        TODO("Not yet implemented")
    }
}
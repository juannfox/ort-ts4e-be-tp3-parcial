package com.example.parcial

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.parcial.models.APIResponse
import com.example.parcial.services.APIClient
import com.example.parcial.services.FlightService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragment container con navgraph
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
        // Barra/menu inferior
        bottomNavView = findViewById(R.id.bottom_bar)
        // Conectar navgraph con menu inferior
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)


        // Launch a coroutine on the main thread
        CoroutineScope(Dispatchers.Main).launch {
            val service = APIClient.create()
            // Fetch the single flights and handle the result
            val trips = FlightService(service).getTrips()
            trips?.forEach { trip ->
                Log.d("MainActivity", """
                    Duration: ${trip.duration}
                    Price: ${trip.price} 
                    From: ${trip.flights.first().departure.id}
                    To: ${trip.flights.last().arrival.id}
                    """)
            }
        }
    }
}

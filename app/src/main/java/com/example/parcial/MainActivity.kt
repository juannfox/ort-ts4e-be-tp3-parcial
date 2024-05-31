package com.example.parcial

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.parcial.models.APIResponse
import com.example.parcial.services.APIClient
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        // TODO borrar ejemplo retrofit
        val service = APIClient.create()

        service.getFlights().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                Log.e("Example", "Esta va segundo")

                if (response.isSuccessful) {
                    val apiResponse = response.body()

                    val activityText = findViewById(R.id.tv_explore) as TextView
                    val airline= apiResponse?.bestFlights?.get(0)?.flights?.get(0)?.airline?.toString()
                    val duration= apiResponse?.bestFlights?.get(0)?.flights?.get(0)?.duration?.toString()
                    val fclass= apiResponse?.bestFlights?.get(0)?.flights?.get(0)?.flightClass?.toString()
                    val departure= apiResponse?.bestFlights?.get(0)?.flights?.get(0)?.departure?.id?.toString()
                    val arrival= apiResponse?.bestFlights?.get(0)?.flights?.get(0)?.arrival?.id?.toString()
                    activityText.text = """
                        Airline: $airline
                        From: $departure
                        To: $arrival
                        Duration: $duration 
                        Class: $fclass
                    """.trimIndent()
                }

            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e("Example", t.stackTraceToString())
            }
        })
    }
}

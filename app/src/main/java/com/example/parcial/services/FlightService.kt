package com.example.parcial.services

import com.example.parcial.models.TripResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO: Usar o quitar

class FlightService @Inject constructor(private val service:FlightInterface) {

    public suspend fun getTrips(): List<TripResponse>? {
        return withContext(Dispatchers.IO) {
            try {
                service.getAllTrips().execute().body()?.bestFlights
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    public suspend fun getTrip(): TripResponse? {
        return withContext(Dispatchers.IO) {
            try {
                getTrips()?.first()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
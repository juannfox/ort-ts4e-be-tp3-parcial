package com.example.parcial.services

import com.example.parcial.models.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlightService @Inject constructor(private val service:FlightInterface) {

    public suspend fun getTrips(): List<Trip>? {
        return withContext(Dispatchers.IO) {
            try {
                service.getAllTrips()
                    .bestFlights
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    public suspend fun getTrip(): Trip? {
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
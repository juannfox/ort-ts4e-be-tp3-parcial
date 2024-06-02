package com.example.parcial.services

import com.example.parcial.models.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightInterface {

    @GET("/search")
    fun getAllTrips(
        @Query("engine") engine: String = "google_flights",
        @Query("departure_id") departureId: String,
        @Query("arrival_id") arrivalId: String,
        @Query("outbound_date") outboundDate: String,
        @Query("return_date") returnDate: String
    ): Call<APIResponse>
}
package com.example.parcial.services

import com.example.parcial.helpers.HttpConst
import com.example.parcial.models.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightInterface {

    @GET("/search")
    fun getAllTrips(
        @Query(HttpConst.SEARCH_ENGINE_KEY) engine: String = HttpConst.SEARCH_ENGINE,
        @Query(HttpConst.DEPARTURE_ID_KEY_PARAM) departureId: String,
        @Query(HttpConst.ARRIVAL_ID_KEY_PARAM) arrivalId: String,
        @Query(HttpConst.OUTBOUND_DATE_KEY_PARAM) outboundDate: String,
        @Query(HttpConst.RETURN_DATE_KEY_PARAM) returnDate: String
    ): Call<APIResponse>
}
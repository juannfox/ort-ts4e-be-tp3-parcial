package com.example.parcial.services

import com.example.parcial.models.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import java.util.Objects

interface FlightService {

    @GET("/search")
    fun getFlights(): Call<APIResponse>
}
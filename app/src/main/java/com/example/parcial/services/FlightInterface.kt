package com.example.parcial.services

import com.example.parcial.models.APIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FlightInterface {

    @GET("/search")
    fun getAllTrips(): Call<APIResponse>
}
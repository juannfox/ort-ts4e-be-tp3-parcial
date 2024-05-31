package com.example.parcial.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private val URL_BASE = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/search"

    // Ejemplo de query
    //?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
package com.example.parcial.models

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("best_flights") val bestFlights: List<Trip>,
    @SerializedName("search_parameters") val searchParameters: SearchParameters,
)

data class SearchParameters(
    @SerializedName("departure_id") val from: String,
    @SerializedName("arrival_id") val to: String,
    @SerializedName("outbound_date") val departureDate: String,
    @SerializedName("return_date") val arrivalDate: String
)

data class Transit(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("time") val time: String,
)

data class Flight(
    @SerializedName("departure_airport") val departure: Transit,
    @SerializedName("arrival_airport") val arrival: Transit,
    @SerializedName("duration") val duration: Number,
    @SerializedName("airline") val airline: String,
    @SerializedName("airline_logo") val logo: String,
    @SerializedName("travel_class") val flightClass: String,
)

data class Trip(
    @SerializedName("flights") val flights: List<Flight>,
    @SerializedName("layovers") val layovers: List<Layover>,
    @SerializedName("total_duration") val duration: Number,
    @SerializedName("price") val price: Number,
    @SerializedName("type") val type: String, // TODO enum
    @SerializedName("airline_logo") val logo: String,
)

data class Layover(
    @SerializedName("duration") val duration: Number,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
)
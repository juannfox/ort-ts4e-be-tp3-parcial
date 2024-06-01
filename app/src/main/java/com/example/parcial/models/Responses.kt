package com.example.parcial.models

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("best_flights") val bestFlights: List<TripResponse>,
    @SerializedName("search_parameters") val searchParameters: SearchParametersResponse,
)

data class SearchParametersResponse(
    @SerializedName("departure_id") val from: String,
    @SerializedName("arrival_id") val to: String,
    @SerializedName("outbound_date") val departureDate: String,
    @SerializedName("return_date") val arrivalDate: String
)

data class TransitResponse(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("time") val time: String,
)

data class FlightResponse(
    @SerializedName("departure_airport") val departure: TransitResponse,
    @SerializedName("arrival_airport") val arrival: TransitResponse,
    @SerializedName("duration") val duration: Number,
    @SerializedName("airplane") val airplane: String,
    @SerializedName("airline") val airline: String,
    @SerializedName("airline_logo") val logo: String,
    @SerializedName("travel_class") val flightClass: String,
    @SerializedName("flight_number") val flightNumber: String,
    @SerializedName("legroom") val legroom: String,
    @SerializedName("overnight") val overnight: Boolean,
)

data class TripResponse(
    @SerializedName("flights") val flights: List<FlightResponse>,
    @SerializedName("layovers") val layovers: List<LayoverResponse>,
    @SerializedName("total_duration") val duration: Number,
    @SerializedName("price") val price: Number,
    @SerializedName("type") val type: String, // TODO enum
    @SerializedName("airline_logo") val logo: String,
)

data class LayoverResponse(
    @SerializedName("duration") val duration: Number,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
)
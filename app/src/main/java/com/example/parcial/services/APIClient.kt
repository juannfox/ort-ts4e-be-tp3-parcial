package com.example.parcial.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    private val defaultLevel = HttpLoggingInterceptor.Level.BASIC
    const val URL_BASE = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"

    // Logger
    private val logger = HttpLoggingInterceptor()
            .setLevel(defaultLevel);

    // HTTP client
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(logger)
        .build()

    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun setLoggingLevel(level: HttpLoggingInterceptor.Level) {
        logger.level = level;
    }

    fun create(): FlightService {
        return retrofit.create(FlightService::class.java)
    }
}
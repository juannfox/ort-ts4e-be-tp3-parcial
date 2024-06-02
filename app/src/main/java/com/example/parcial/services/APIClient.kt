package com.example.parcial.services

import com.example.parcial.helpers.HttpConst
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    private val defaultLevel = HttpLoggingInterceptor.Level.BASIC

    // Logger
    private val logger = HttpLoggingInterceptor()
            .setLevel(defaultLevel);

    // HTTP client
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(logger) // Logger para requests y responses HTTP
        .addNetworkInterceptor(AuthInterceptor) // Interceptor para autenticar requests
        .build()

    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(HttpConst.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun setLoggingLevel(level: HttpLoggingInterceptor.Level) {
        logger.level = level;
    }

    fun create(): FlightInterface {
        return retrofit.create(FlightInterface::class.java)
    }

    object AuthInterceptor : Interceptor {
        /**
         * Middleware para interceptar requests y agregar query param con API Key
         */

        override fun intercept(chain: Interceptor.Chain): Response {
            /**
            * Inyectar query param con API Key para Autenticar requests
            */

            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter(HttpConst.API_KEY_PARAM, HttpConst.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)
        }
    }
}
package com.example.pwr.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val OMDB_API_KEY: String = "ec53e7de"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: OmdbApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)
    }

    val apiKey: String = OMDB_API_KEY
}

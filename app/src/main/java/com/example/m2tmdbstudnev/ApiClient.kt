package com.example.m2tmdbstudnev

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        private const val TMDB_URL = "https://api.themoviedb.org"

        val repo: ITmdbApi = Retrofit.Builder()
            .baseUrl(TMDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ITmdbApi::class.java)
    }

}

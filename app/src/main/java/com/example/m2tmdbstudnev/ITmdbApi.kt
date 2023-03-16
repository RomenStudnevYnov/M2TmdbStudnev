package com.example.m2tmdbstudnev

import com.example.m2tmdbstudnev.model.PopularPersonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITmdbApi {

    @GET("/3/person/popular")
    fun getPopularPeople(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<PopularPersonResponse>

}
package com.example.m2tmdbstudnev.model

import com.google.gson.annotations.SerializedName


data class PopularPersonResponse (

  @SerializedName("page"          ) var page         : Int?               = null,
  @SerializedName("results"       ) var results      : ArrayList<Person>  = arrayListOf(),
  @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
  @SerializedName("total_results" ) var totalResults : Int?               = null

)
package com.example.unproje

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getCard(
        @Query("apiKey")key:String,
        @Query("country")country:String,
        @Query("category")category:String

    ):Call<NewsResponse>


}

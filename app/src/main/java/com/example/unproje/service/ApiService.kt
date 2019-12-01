package com.example.unproje.service

import com.example.unproje.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("top-headlines")
    fun getCard(
        @Query("apiKey")key:String,
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("pageSize")page:Int

    ): Call<NewsResponse>


}
package com.example.unproje

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language")language:String,
        @Query("region")region:String,
        @Query("page")page:Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("language")language:String,
        @Query("region")region:String,
        @Query("page")page:Int,
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

}

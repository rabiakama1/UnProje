package com.example.unproje

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("results")
    val results: ArrayList<Movies>?=null
}

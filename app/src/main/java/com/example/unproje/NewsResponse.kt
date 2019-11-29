package com.example.unproje

import com.google.gson.annotations.SerializedName

class NewsResponse {
    @SerializedName("articles")
    val totalResults: ArrayList<News>?=null

}
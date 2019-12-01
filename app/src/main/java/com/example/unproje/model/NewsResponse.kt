package com.example.unproje.model

import com.example.unproje.model.News
import com.google.gson.annotations.SerializedName

class NewsResponse {
    @SerializedName("articles")
    val totalResults: ArrayList<News>?=null

}
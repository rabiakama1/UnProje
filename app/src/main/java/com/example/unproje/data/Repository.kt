package com.example.unproje.data

import com.example.unproje.BuildConfig
import com.example.unproje.service.ApiService

class Repository(private val api: ApiService) {

    fun getNews()=api.getCard(key = BuildConfig.NewsApiKey, country = "tr",category="science",page = 70)

}



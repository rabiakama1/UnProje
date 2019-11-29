package com.example.unproje

class Repository(private val api: ApiService) {

    fun getNews()=api.getCard(key = BuildConfig.NewsApiKey, country = "tr",category="technology")

}



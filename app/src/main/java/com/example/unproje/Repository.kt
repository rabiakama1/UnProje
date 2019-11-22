package com.example.unproje

class Repository(private val api: ApiService) {

    fun getPopularMovies()=api.getPopularMovies(apiKey =  BuildConfig.ApiKey,language = "en-US",page = 1,region="TR")
  /*  fun getNowPlayingMovies()=api.getNowPlayingMovies(apiKey =  BuildConfig.ApiKey,language = "en-US",page = 1,region="TR")
    fun getMovies()= api.getMovie(apiKey = BuildConfig.ApiKey,language = "en-US",region = "TR",sortBy = "original_title.desc",page=1)*/
    fun getTopMovies() = api.getTopRatedMovies(apiKey =BuildConfig.ApiKey,language = "en-US",page = 1,region = "TR")

}



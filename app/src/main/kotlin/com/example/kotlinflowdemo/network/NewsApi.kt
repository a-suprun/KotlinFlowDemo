package com.example.kotlinflowdemo.network

import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=us")
    suspend fun getTopHeadlines(): NewsResponse
}
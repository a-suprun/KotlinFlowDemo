package com.example.kotlinflowdemo.repository

import com.example.kotlinflowdemo.network.NewsResponse
import com.example.kotlinflowdemo.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class MainRepository {

    private val mNewsApi = RetrofitClient.getNewsApi()

    val topHeadlines: Flow<NewsResponse> = flow {
        emit(getTopHeadlines())
    }.flowOn(Dispatchers.IO)

    private suspend fun getTopHeadlines(): NewsResponse =
           mNewsApi.getTopHeadlines()

}
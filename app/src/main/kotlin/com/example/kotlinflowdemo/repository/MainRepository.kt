package com.example.kotlinflowdemo.repository

import com.example.kotlinflowdemo.network.RetrofitClient
import com.example.kotlinflowdemo.viewmodel.Result
import retrofit2.HttpException

internal class MainRepository {

    private val mNewsApi = RetrofitClient.getNewsApi()

//    val topHeadlines: Flow<NewsResponse> = flow {
//        emit(getTopHeadlines())
//    }.flowOn(Dispatchers.IO)

    suspend fun getTopHeadlines(): Result {
        return try {
            Result.Success(mNewsApi.getTopHeadlines().articles)
        } catch (e: HttpException) {
            Result.Error(e.message())
        }
    }

}
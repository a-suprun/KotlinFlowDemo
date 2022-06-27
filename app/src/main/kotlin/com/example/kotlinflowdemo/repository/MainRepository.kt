package com.example.kotlinflowdemo.repository

import com.example.kotlinflowdemo.network.RetrofitClient
import com.example.kotlinflowdemo.viewmodel.Result
import kotlinx.coroutines.delay
import retrofit2.HttpException

internal class MainRepository {

    private val mNewsApi = RetrofitClient.getNewsApi()

    suspend fun getTopHeadlines(): Result {
        return try {
            delay(1000)
            Result.Success(mNewsApi.getTopHeadlines().articles)
        } catch (e: HttpException) {
            Result.Error(e.message())
        }
    }

}
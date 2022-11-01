package com.example.kotlinflowdemo.network.interceptor

import com.example.kotlinflowdemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(chain.request().newBuilder().addHeader("Authorization", BuildConfig.API_KEY).build())
    }
}
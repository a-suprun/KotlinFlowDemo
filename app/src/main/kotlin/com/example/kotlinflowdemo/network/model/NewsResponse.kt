package com.example.kotlinflowdemo.network.model

data class NewsResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>
)
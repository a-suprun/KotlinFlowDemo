package com.example.kotlinflowdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowdemo.network.Article
import com.example.kotlinflowdemo.network.NewsResponse
import com.example.kotlinflowdemo.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MainViewModel : ViewModel() {

    private val mRepository = MainRepository()
    private val _newsFlow: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState.Success(emptyList()))
    val newsFlow: StateFlow<NewsUiState> = _newsFlow

    init {
        viewModelScope.launch {
            mRepository.topHeadlines
                .map { it.articles }
                .catch { error ->
                    _newsFlow.value = NewsUiState.Error(error)
                }
                .collect { news: List<Article> ->
                    _newsFlow.value = NewsUiState.Success(news)
                }
        }
    }

}

sealed class NewsUiState {
    data class Success(val news: List<Article>): NewsUiState()
    data class Error(val exception: Throwable): NewsUiState()
}
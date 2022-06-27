package com.example.kotlinflowdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowdemo.network.Article
import com.example.kotlinflowdemo.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel : ViewModel() {

    private val mRepository = MainRepository()
    private val _newsFlow: MutableStateFlow<Result> = MutableStateFlow(Result.Success(emptyList()))
    val newsFlow: StateFlow<Result> = _newsFlow

    init {
        _newsFlow.value = Result.Loading(null)
        viewModelScope.launch {
            _newsFlow.update {
                mRepository.getTopHeadlines()
            }
        }
    }

    fun reloadNews() {
        _newsFlow.value = Result.Loading(null)
        viewModelScope.launch {
            _newsFlow.update {
                mRepository.getTopHeadlines()
            }
        }
    }

}

sealed class Result {
    data class Success(val news: List<Article>) : Result()
    data class Loading(val message: String?) : Result()
    data class Error(val errorMessage: String?) : Result()
}
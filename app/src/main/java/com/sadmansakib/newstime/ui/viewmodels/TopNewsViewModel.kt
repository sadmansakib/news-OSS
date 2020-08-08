package com.sadmansakib.newstime.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadmansakib.newstime.models.NewsResponse
import com.sadmansakib.newstime.network.DataState
import com.sadmansakib.newstime.repositories.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TopNewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    val breakingNews: MutableLiveData<DataState<NewsResponse>> = MutableLiveData()
    private var breakingNewsPage = 1

    init {
        getTopNews(country = "us")
    }

    @ExperimentalCoroutinesApi
    private fun getTopNews(country: String)= viewModelScope.launch {
        newsRepository.getTopNews(country, category="technology" , page= breakingNewsPage)
            .onEach {
                dataState ->  breakingNews.value = dataState
            }
            .launchIn(viewModelScope)
    }
}
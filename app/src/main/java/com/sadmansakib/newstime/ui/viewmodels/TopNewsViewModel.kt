package com.sadmansakib.newstime.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadmansakib.newstime.models.NewsResponse
import com.sadmansakib.newstime.repositories.NewsRepository
import kotlinx.coroutines.launch

class TopNewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    val breakingNews: MutableLiveData<NewsResponse> = MutableLiveData()
    private var breakingNewsPage = 1

    init {
        getTopNews(country = "us")
    }

    fun getTopNews(country: String)= viewModelScope.launch {
        val response = newsRepository.getTopNews(country, category="technology" , page= breakingNewsPage)
        breakingNews.postValue(response.body())
    }
}
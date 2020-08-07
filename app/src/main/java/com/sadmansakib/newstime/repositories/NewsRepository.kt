package com.sadmansakib.newstime.repositories

import com.sadmansakib.newstime.network.NewsNetworkInterface
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val networkInterface: NewsNetworkInterface
) {
    suspend fun getTopNews(countryCode: String,
                           category: String ,
                           page: Int) = networkInterface.getTopNews(countryCode, category, page)
}
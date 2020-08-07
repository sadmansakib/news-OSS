package com.sadmansakib.newstime.network

import com.sadmansakib.newstime.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsNetworkInterface {

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        country : String = "us",
        @Query("category")
        category: String = "technology",
        @Query("page")
        page: Int = 1
    ): Response<NewsResponse>
}
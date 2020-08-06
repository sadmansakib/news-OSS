package com.sadmansakib.newstime.network

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
    )
}
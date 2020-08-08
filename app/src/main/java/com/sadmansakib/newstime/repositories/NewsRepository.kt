package com.sadmansakib.newstime.repositories

import com.sadmansakib.newstime.models.NewsResponse
import com.sadmansakib.newstime.network.DataState
import com.sadmansakib.newstime.network.NewsNetworkInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val networkInterface: NewsNetworkInterface
) {
    suspend fun getTopNews(countryCode: String, 
                           category: String ,
                           page: Int): Flow<DataState<NewsResponse>> = flow {
        emit(DataState.LOADING)
        try {
            val response = networkInterface.getTopNews(
                countryCode,category,page
            )
            if (response.isSuccessful){
                response.body()?.let {newsResponse ->
                    emit(DataState.SUCCESS(newsResponse))
                }
            }
        }catch (e : Exception){
            emit(DataState.ERROR(e))
        }
    }
}
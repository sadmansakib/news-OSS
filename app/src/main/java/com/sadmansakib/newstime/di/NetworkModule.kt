package com.sadmansakib.newstime.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sadmansakib.newstime.network.NewsNetworkInterface
import com.sadmansakib.newstime.network.interceptor.AuthHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson():Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideNetworkClient(authHeaderInterceptor: AuthHeaderInterceptor):OkHttpClient{
        return OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(authHeaderInterceptor)
        }.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideNewsNetwork(retrofit: Retrofit.Builder): NewsNetworkInterface = retrofit
        .build().create(NewsNetworkInterface::class.java)

}
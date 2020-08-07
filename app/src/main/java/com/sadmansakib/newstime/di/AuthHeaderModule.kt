package com.sadmansakib.newstime.di

import com.sadmansakib.newstime.network.interceptor.AuthHeaderInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor

@InstallIn(ApplicationComponent::class)
@Module
abstract class AuthHeaderModule {
    
    @Binds
    abstract fun bindAuthHeaderInterceptor(authHeaderInterceptor: AuthHeaderInterceptor): Interceptor
}
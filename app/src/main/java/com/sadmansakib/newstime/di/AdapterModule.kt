package com.sadmansakib.newstime.di

import com.sadmansakib.newstime.ui.adapters.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class AdapterModule {

    @Provides
    fun provideRecyclerAdapter():NewsAdapter{
        return NewsAdapter()
    }
}
package com.sadmansakib.newstime.network

sealed class DataState<out T> {
    data class SUCCESS<out T>(val data: T): DataState<T>()

    data class ERROR(val exception: Exception): DataState<Nothing>()

    object LOADING: DataState<Nothing>()
}
package com.sadmansakib.newstime.network.interceptor

import com.sadmansakib.newstime.others.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        req = req.newBuilder().apply {
            header("X-Api-Key",API_KEY)
        }.build()
        return  chain.proceed(req)
    }
}
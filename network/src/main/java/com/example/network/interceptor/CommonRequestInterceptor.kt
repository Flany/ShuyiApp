package com.example.network.interceptor

import com.example.common.ShuyiConfig
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader("os", "android")
        newBuilder.addHeader("appVersion", ShuyiConfig.appVersionCode)
        return chain.proceed(newBuilder.build())
    }
}
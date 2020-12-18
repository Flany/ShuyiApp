package com.example.network.interceptor

import com.example.base.SyAppConfig
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader("os", "android")
        newBuilder.addHeader("appVersion", SyAppConfig.appVersionCode)
        return chain.proceed(newBuilder.build())
    }
}
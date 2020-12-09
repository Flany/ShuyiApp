package com.example.network.interceptor

import com.example.common.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        LogUtils.d(
            CommonResponseInterceptor::class.java.name,
            "requestTime: ${System.currentTimeMillis() - startTime}"
        )
        return response
    }
}
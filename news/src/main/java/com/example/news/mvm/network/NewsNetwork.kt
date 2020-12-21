package com.example.news.mvm.network

import com.example.network.core.BaseNetwork
import okhttp3.Interceptor

class NewsNetwork : BaseNetwork() {

    override fun getInterceptor(): Interceptor? {
        return null
    }

    override fun getHostApi(): String {
        return "https://www.wanandroid.com/"
    }

    override fun getTestHostApi(): String {
        return "https://www.wanandroid.com/"
    }
}
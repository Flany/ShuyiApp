package com.example.news.mvm.net

import com.example.network.core.BaseNetwork
import okhttp3.Interceptor

class NewsRemoteResp : BaseNetwork() {

    override fun getInterceptor(): Interceptor? {
        return null
    }

    override fun getHostApi(): String {
        return "https://gank.io/"
    }

    override fun getTestHostApi(): String {
        return "https://gank.io/"
    }
}
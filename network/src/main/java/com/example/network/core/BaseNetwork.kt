package com.example.network.core

import com.example.base.SyAppConfig
import com.example.network.interceptor.CommonRequestInterceptor
import com.example.network.interceptor.CommonResponseInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseNetwork : IEnvironment {

    companion object {
        const val CACHE_SIZE = 10 * 1024 * 1024L
    }

    private var mOkHttpClient: OkHttpClient? = null
    private val mRetrofitHashMap = hashMapOf<String, Retrofit>()

    fun <T> getService(service: Class<T>): T {
        return initRetrofit(service).create(service)
    }

    private fun <T> initRetrofit(service: Class<T>): Retrofit {
        val baseUrl = if (!SyAppConfig.isDebug) {
            getHostApi()
        } else {
            getTestHostApi()
        }
        val key = baseUrl + service.name
        if (mRetrofitHashMap.containsKey(key)) {
            val retrofit = mRetrofitHashMap[key]
            if (retrofit != null) {
                return retrofit
            }
        }
        if (mOkHttpClient == null) {
            initOkHttpClient()
        }
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
        mOkHttpClient?.apply {
            builder.client(this)
        }
        builder.addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        mRetrofitHashMap[key] = retrofit
        return retrofit
    }

    private fun initOkHttpClient() {
        if (mOkHttpClient == null) {
            val builder = OkHttpClient.Builder()
            SyAppConfig.applicationContext?.apply {
                builder.cache(Cache(cacheDir, CACHE_SIZE))
            }
            getInterceptor()?.apply {
                builder.addInterceptor(this)
            }
            builder.addInterceptor(CommonRequestInterceptor())
            builder.addInterceptor(CommonResponseInterceptor())
            if (SyAppConfig.isDebug) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(httpLoggingInterceptor)
            }
            mOkHttpClient = builder.build()
        }
    }

    abstract fun getInterceptor(): Interceptor?
}
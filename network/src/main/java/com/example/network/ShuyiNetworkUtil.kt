package com.example.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ShuyiNetworkUtil {

    fun create(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .connectTimeout(60000, TimeUnit.MILLISECONDS).build()

        return Retrofit.Builder()
            .baseUrl("https://gank.io/") //设置网络请求的Url地址
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}
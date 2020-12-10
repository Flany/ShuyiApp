package com.example.news.mvm.network.api

import com.example.news.mvm.network.bean.NewsData
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {

    @GET("api/v2/data/category/Girl/type/Girl/page/1/count/10")
    fun getNewsData(): Call<NewsData>
}
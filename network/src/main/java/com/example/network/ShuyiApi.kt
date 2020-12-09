package com.example.network

import com.example.network.bean.NewsData
import retrofit2.Call
import retrofit2.http.GET

interface ShuyiApi {

    @GET("api/v2/data/category/Girl/type/Girl/page/1/count/10")
    fun getGirls(): Call<NewsData>
}
package com.example.news.mvm.network.api

import com.example.news.mvm.network.bean.ArticlePageData
import com.example.news.mvm.network.bean.BaseNewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("article/list/{path}/json")
    suspend fun getNewsList(@Path("path") path: Int): BaseNewsResponse<ArticlePageData>
}
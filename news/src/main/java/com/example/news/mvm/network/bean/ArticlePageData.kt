package com.example.news.mvm.network.bean

data class ArticlePageData(
    val curPage: Int,
    val datas: List<ArticleData>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
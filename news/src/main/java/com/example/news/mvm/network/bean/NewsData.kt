package com.example.news.mvm.network.bean

data class NewsData(
    var data: MutableList<NewsItemData>,
    var page: Int,
    var page_count: Int,
    var status: Int,
    var total_counts: Int
)
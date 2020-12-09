package com.example.network.bean

data class NewsData(
    var data: MutableList<GirlData>,
    var page: Int,
    var page_count: Int,
    var status: Int,
    var total_counts: Int
)
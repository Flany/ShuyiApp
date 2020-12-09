package com.example.network.bean

data class GirlData(
    var _id: String? = null,
    var author: String? = null,
    var category: String? = null,
    var createdAt: String? = null,
    var desc: String? = null,
    var images: MutableList<String>? = null,
    var likeCounts: Int = 0,
    var publishedAt: String? = null,
    var stars: Int = 0,
    var title: String? = null,
    var type: String? = null,
    var url: String? = null,
    var views: Long = 0
)
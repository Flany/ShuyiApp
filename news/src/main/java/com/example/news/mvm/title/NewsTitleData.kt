package com.example.news.mvm.title

import com.example.base.recyclerview.BaseData

class NewsTitleData(
    val author: String,
    val shareDate: String,
    val superChapterName: String,
    override val title: String, override val linkUrl: String,
    override val pageCount: Int = 0
) : BaseData(title, linkUrl)
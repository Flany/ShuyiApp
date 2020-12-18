package com.example.news.mvm.title

import com.example.base.recyclerview.BaseViewModel

class NewsTitleModel(
    val author: String,
    val shareDate: String,
    val superChapterName: String,
    override val title: String, override val linkUrl: String
) : BaseViewModel(title, linkUrl)
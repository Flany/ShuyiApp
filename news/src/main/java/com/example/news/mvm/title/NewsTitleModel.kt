package com.example.news.mvm.title

import com.example.base.core.BaseViewModel

class NewsTitleModel(var newsTitle: String, var newsJumpUrl: String) :
    BaseViewModel(newsTitle, newsJumpUrl)
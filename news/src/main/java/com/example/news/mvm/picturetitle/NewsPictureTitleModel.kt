package com.example.news.mvm.picturetitle

import com.example.base.core.BaseViewModel

class NewsPictureTitleModel(
    var newsTitle: String,
    var newsJumpUrl: String,
    var newsPictureUrl: String
) : BaseViewModel(newsTitle, newsJumpUrl)
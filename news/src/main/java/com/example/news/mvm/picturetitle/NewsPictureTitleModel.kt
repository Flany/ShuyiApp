package com.example.news.mvm.picturetitle

import com.example.base.recyclerview.BaseModel

class NewsPictureTitleModel(
    var newsTitle: String,
    var newsJumpUrl: String,
    var newsPictureUrl: String
) : BaseModel(newsTitle, newsJumpUrl)
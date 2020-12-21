package com.example.news.mvm.mapper

import com.example.base.vm.data.Mapper
import com.example.news.mvm.network.bean.ArticleData
import com.example.news.mvm.title.NewsTitleData
import javax.inject.Inject

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:
 */
class NewsMapper @Inject constructor() : Mapper<ArticleData, NewsTitleData> {
    override fun map(input: ArticleData): NewsTitleData {
        return NewsTitleData(
            input.author,
            input.niceShareDate,
            input.superChapterName,
            input.title,
            input.link
        )
    }
}
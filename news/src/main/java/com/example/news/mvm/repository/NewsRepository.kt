package com.example.news.mvm.repository

import com.example.base.core.BaseViewModel
import com.example.news.mvm.network.NewsRemoteResp
import com.example.news.mvm.network.api.NewsApi
import com.example.news.mvm.title.NewsTitleModel
import java.lang.RuntimeException

class NewsRepository {

    suspend fun getNewsList(page: String): MutableList<BaseViewModel> {
        val response = NewsRemoteResp().getService(NewsApi::class.java).getNewsList(page)
        if (response.isSuccess()) {
            val newsTitleModelList = mutableListOf<BaseViewModel>()
            response.data.datas.forEach {
                newsTitleModelList.add(
                    NewsTitleModel(
                        it.author,
                        it.niceShareDate,
                        it.superChapterName,
                        it.title,
                        it.link
                    )
                )
            }
            return newsTitleModelList
        } else {
            throw RuntimeException()
        }
    }
}
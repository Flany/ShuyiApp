package com.example.news.mvm.repository

import com.example.base.recyclerview.BaseViewModel
import com.example.news.mvm.network.NewsNetwork
import com.example.news.mvm.network.api.NewsApi
import com.example.news.mvm.title.NewsTitleModel
import java.lang.RuntimeException
import javax.inject.Inject

class NewsRepository @Inject constructor() {

    suspend fun getNewsList(page: String): MutableList<BaseViewModel> {
        val response = NewsNetwork().getService(NewsApi::class.java).getNewsList(page)
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
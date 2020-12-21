package com.example.news.mvm.repository

import com.example.base.recyclerview.BaseData
import com.example.base.vm.IBaseRepository
import com.example.base.vm.data.BaseResult
import com.example.base.vm.data.PageResult
import com.example.news.mvm.network.NewsNetwork
import com.example.news.mvm.network.api.NewsApi
import com.example.news.mvm.title.NewsTitleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor() : IBaseRepository {

    override suspend fun loadData(): Flow<BaseResult<MutableList<BaseData>>> {
        return flow { }
    }

    override suspend fun loadDataByPage(page: Int): Flow<BaseResult<PageResult<BaseData>>> {
        return flow {
            try {
                val response = NewsNetwork().getService(NewsApi::class.java).getNewsList(page)
                val newsTitleModelList = mutableListOf<BaseData>()
                if (response.isSuccess()) {
                    response.data.datas.forEach {
                        newsTitleModelList.add(
                            NewsTitleData(
                                it.author,
                                it.niceShareDate,
                                it.superChapterName,
                                it.title,
                                it.link
                            )
                        )
                    }
                    emit(
                        BaseResult.Success(
                            PageResult(
                                response.data.curPage == response.data.pageCount,
                                newsTitleModelList
                            )
                        )
                    )
                } else {
                    emit(BaseResult.Failure(Throwable(response.errorMsg)))
                }
            } catch (exception: Exception) {
                emit(BaseResult.Failure(Throwable()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
package com.example.news.mvm.repository

import com.example.base.recyclerview.BaseData
import com.example.base.vm.IBaseRepository
import com.example.base.vm.data.RepositoryResult
import com.example.base.vm.data.PageResult
import com.example.news.mvm.mapper.NewsMapper
import com.example.news.mvm.network.NewsNetwork
import com.example.news.mvm.network.api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(private val mapper: NewsMapper) : IBaseRepository {

    override suspend fun loadData(): Flow<RepositoryResult<MutableList<BaseData>>> {
        return flow { }
    }

    override suspend fun loadDataByPage(page: Int): Flow<RepositoryResult<PageResult<BaseData>>> {
        return flow {
            try {
                val response = NewsNetwork().getService(NewsApi::class.java).getNewsList(page)
                val newsTitleModelList = mutableListOf<BaseData>()
                if (response.isSuccess()) {
                    response.data.datas.forEach {
                        newsTitleModelList.add(mapper.map(it))
                    }
                    emit(
                        RepositoryResult.Success(
                            PageResult(
                                response.data.curPage == response.data.pageCount,
                                newsTitleModelList
                            )
                        )
                    )
                } else {
                    emit(RepositoryResult.Failure(Throwable(response.errorMsg)))
                }
            } catch (exception: Exception) {
                emit(RepositoryResult.Failure(Throwable(exception.message)))
            }
        }.flowOn(Dispatchers.IO)
    }
}
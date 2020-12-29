package com.android.scan.repository

import com.example.base.recyclerview.BaseData
import com.example.base.vm.IBaseRepository
import com.example.base.vm.data.PageResult
import com.example.base.vm.data.RepositoryResult
import kotlinx.coroutines.flow.Flow

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
class ScanRepository :IBaseRepository{
    override suspend fun loadData(): Flow<RepositoryResult<MutableList<BaseData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadDataByPage(page: Int): Flow<RepositoryResult<PageResult<BaseData>>> {
        TODO("Not yet implemented")
    }
}
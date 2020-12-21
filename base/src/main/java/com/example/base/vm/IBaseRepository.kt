package com.example.base.vm

import com.example.base.recyclerview.BaseData
import com.example.base.vm.data.BaseResult
import com.example.base.vm.data.PageResult
import kotlinx.coroutines.flow.Flow

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:
 */
interface IBaseRepository {

    suspend fun loadData(): Flow<BaseResult<MutableList<BaseData>>>

    suspend fun loadDataByPage(page: Int): Flow<BaseResult<PageResult<BaseData>>>
}
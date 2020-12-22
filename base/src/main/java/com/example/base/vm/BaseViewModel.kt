package com.example.base.vm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.recyclerview.BaseData
import com.example.base.state.LoadState
import com.example.base.vm.data.RepositoryResult
import com.example.base.vm.data.doFailure
import com.example.base.vm.data.doSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/18
 * @since:  基础的ViewModel
 */
@ExperimentalCoroutinesApi
abstract class BaseViewModel(
    private var isPagingModel: Boolean = false,
    private val initPageNumber: Int = -1,
    private val repository: IBaseRepository
) : ViewModel(), LifecycleObserver {

    val data = MutableLiveData<MutableList<BaseData>>()
    val failure = MutableLiveData<String>()
    val loadState = MutableLiveData<LoadState>()

    private var isLoading: Boolean = false
    private var mCurrentPageNumber: Int = initPageNumber

    fun loadData() {
        if (!isLoading) {
            if (isPagingModel) {
                mCurrentPageNumber = initPageNumber
            }
            isLoading = true
            loadState.postValue(LoadState.LOADING)
            load()
        }
    }

    fun loadDataByPage() {
        if (!isLoading) {
            isLoading = true
            ++mCurrentPageNumber
            load()
        }
    }

    private fun load() = viewModelScope.launch {
        repository.loadDataByPage(mCurrentPageNumber)
            .onStart {
                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
            }
            .catch { cause ->
                // 捕获上游出现的异常
                emit(RepositoryResult.Failure(cause))
            }
            .onCompletion {
                // 请求完成
                isLoading = false
            }
            .collectLatest { result ->
                result.doFailure { throwable ->
                    failure.postValue(throwable?.message ?: "failure")
                    loadState.postValue(LoadState.FAILED)
                }
                result.doSuccess { pageResult ->
                    val value = pageResult.data
                    if (!isPagingModel) {
                        if (value.isNullOrEmpty()) {
                            loadState.postValue(LoadState.EMPTY)
                        } else {
                            data.postValue(pageResult.data)
                            loadState.postValue(LoadState.SUCCESS)
                        }
                        return@doSuccess
                    }
                    if (mCurrentPageNumber == initPageNumber) {
                        data.postValue(value)
                    } else {
                        val newResult = data.value ?: mutableListOf()
                        newResult.addAll(value)
                        data.postValue(newResult)
                    }
                    loadState.postValue(LoadState.SUCCESS)
                }
            }
    }
}
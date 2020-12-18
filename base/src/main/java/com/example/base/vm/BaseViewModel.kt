package com.example.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.state.LoadState

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/18
 * @since:  基础的ViewModel
 */
abstract class BaseViewModel : ViewModel() {

    val loadState = MutableLiveData<LoadState>()

    abstract fun loadData()
}
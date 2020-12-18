package com.example.news.mvm.title

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.recyclerview.BaseModel
import com.example.base.state.LoadState
import com.example.base.vm.BaseViewModel
import com.example.news.mvm.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsTitleViewModel @ViewModelInject constructor(val repository: NewsRepository) :
    BaseViewModel() {

    val mNewsTitleModelList = MutableLiveData<MutableList<BaseModel>>()

    fun getNewsList(page: String) {
        loadState.postValue(LoadState.LOADING)
        viewModelScope.launch {
            mNewsTitleModelList.postValue(repository.getNewsList(page))
            loadState.postValue(LoadState.SUCCESS)
        }
    }

    override fun loadData() {

    }
}
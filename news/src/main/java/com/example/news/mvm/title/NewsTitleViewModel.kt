package com.example.news.mvm.title

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.core.BaseViewModel
import com.example.news.mvm.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsTitleViewModel : ViewModel() {

    val mNewsTitleModel = MutableLiveData<BaseViewModel>()
    val mNewsTitleModelList = MutableLiveData<MutableList<BaseViewModel>>()

    private val newsRepository by lazy {
        NewsRepository()
    }

    fun getNewsList(page: String) {
        viewModelScope.launch {
            mNewsTitleModelList.postValue(newsRepository.getNewsList(page))
        }
    }
}
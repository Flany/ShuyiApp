package com.example.news.mvm.title

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.core.BaseViewModel
import com.example.news.mvm.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsTitleViewModel @ViewModelInject constructor(val repository: NewsRepository) :
    ViewModel() {

    val mNewsTitleModelList = MutableLiveData<MutableList<BaseViewModel>>()

    fun getNewsList(page: String) {
        viewModelScope.launch {
            mNewsTitleModelList.postValue(repository.getNewsList(page))
        }
    }
}
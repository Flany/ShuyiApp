package com.example.news.mvm

import androidx.hilt.lifecycle.ViewModelInject
import com.example.base.vm.BaseViewModel
import com.example.news.mvm.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NewsTitleViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : BaseViewModel(true, 1, repository)
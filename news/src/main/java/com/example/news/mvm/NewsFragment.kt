package com.example.news.mvm

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.base.recyclerview.BaseViewModel
import com.example.news.R
import com.example.news.databinding.XNewsFragmentBinding
import com.example.news.mvm.title.NewsTitleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<XNewsFragmentBinding>() {

    private val mNewsItemAdapter by lazy {
        NewsItemAdapter()
    }
    private val newsTitleViewModel by lazy {
        ViewModelProvider(this).get(NewsTitleViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.x__news_fragment
    }

    override fun initViews() {
        mBinding?.recyclerview?.adapter = mNewsItemAdapter
        newsTitleViewModel.mNewsTitleModelList.observe(this, Observer<MutableList<BaseViewModel>> {
            it?.apply {
                mNewsItemAdapter.setData(this)
            }
        })
        newsTitleViewModel.getNewsList("0")
    }

    override fun getStatName(): String {
        return javaClass.name
    }
}
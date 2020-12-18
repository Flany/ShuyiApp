package com.example.news.mvm

import androidx.lifecycle.Observer
import com.example.base.BaseFragment
import com.example.base.recyclerview.BaseModel
import com.example.news.R
import com.example.news.databinding.XNewsFragmentBinding
import com.example.news.mvm.title.NewsTitleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<XNewsFragmentBinding, NewsTitleViewModel>() {

    private val mNewsItemAdapter by lazy {
        NewsItemAdapter()
    }

    override fun getLayoutId(): Int {
        return R.layout.x__news_fragment
    }

    override fun initViews() {
        mBinding?.recyclerview?.adapter = mNewsItemAdapter
        mViewModel.mNewsTitleModelList.observe(this, Observer<MutableList<BaseModel>> {
            it?.apply {
                mNewsItemAdapter.setData(this)
            }
        })
        mViewModel.getNewsList("0")
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun getViewModelClass(): Class<NewsTitleViewModel> {
        return NewsTitleViewModel::class.java
    }
}
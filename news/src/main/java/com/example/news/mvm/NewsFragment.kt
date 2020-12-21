package com.example.news.mvm

import androidx.lifecycle.Observer
import com.example.base.BaseFragment
import com.example.base.recyclerview.BaseData
import com.example.news.R
import com.example.news.databinding.XNewsFragmentBinding
import com.example.news.mvm.title.NewsItemAdapter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class NewsFragment : BaseFragment<XNewsFragmentBinding, NewsTitleViewModel>() {

    private val mNewsItemAdapter by lazy {
        NewsItemAdapter()
    }

    override fun getLayoutId(): Int {
        return R.layout.x__news_fragment
    }

    override fun initViews() {
        mBinding?.recyclerview?.adapter = mNewsItemAdapter
        mBinding?.refreshLayout?.apply {
            setEnableLoadMore(true)
            setRefreshHeader(ClassicsHeader(context))
            setRefreshFooter(ClassicsFooter(context))
            setOnRefreshListener {
                mViewModel.loadData()
            }
            setOnLoadMoreListener {
                mViewModel.loadDataByPage()
            }
        }
        mViewModel.data.observe(this, Observer<MutableList<BaseData>> {
            it?.apply {
                mNewsItemAdapter.setData(this)
            }
        })
        mViewModel.loadData()
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun getViewModelClass(): Class<NewsTitleViewModel> {
        return NewsTitleViewModel::class.java
    }

    override fun onDataResponded(data: MutableList<BaseData>) {
        mNewsItemAdapter.setData(data)
    }

    override fun onStatusResponded() {
        mBinding?.refreshLayout?.apply {
            finishRefresh()
            finishLoadMore()
        }
    }
}
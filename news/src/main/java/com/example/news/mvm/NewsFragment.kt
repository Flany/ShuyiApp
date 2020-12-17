package com.example.news.mvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.core.BaseViewModel
import com.example.news.R
import com.example.news.databinding.XNewsFragmentBinding
import com.example.news.mvm.title.NewsTitleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var mBinding: XNewsFragmentBinding? = null

    private val mNewsItemAdapter by lazy {
        NewsItemAdapter(mutableListOf())
    }
    private val newsTitleViewModel by lazy {
        ViewModelProvider(this).get(NewsTitleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.x__news_fragment, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mNewsItemAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        newsTitleViewModel.mNewsTitleModelList.observe(this,
            Observer<MutableList<BaseViewModel>> {
                it?.let { data ->
                    mNewsItemAdapter.setData(data)
                }
            })
        newsTitleViewModel.getNewsList("0")
    }
}
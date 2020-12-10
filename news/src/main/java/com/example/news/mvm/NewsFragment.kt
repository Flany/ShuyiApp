package com.example.news.mvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.core.BaseViewModel
import com.example.news.R
import com.example.news.databinding.XNewsFragmentBinding
import com.example.news.mvm.network.NewsRemoteResp
import com.example.news.mvm.network.api.NewsApi
import com.example.news.mvm.picturetitle.NewsPictureTitleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsFragment : Fragment() {

    private var mNewsItemAdapter: NewsItemAdapter? = null
    private var mBinding: XNewsFragmentBinding? = null

    companion object {
        fun launch(): NewsFragment {
            val fragment = NewsFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.x__news_fragment, container, false)
        return mBinding?.root
    }

    override fun onStart() {
        super.onStart()
        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            val items = mutableListOf<BaseViewModel>()
            val result = withContext(Dispatchers.IO) {
                NewsRemoteResp().getService(NewsApi::class.java).getNewsData().execute()
            }
            result.body()?.data?.forEach {
                items.add(NewsPictureTitleModel(it.desc!!, it.url!!, it.images!![0]))
            }
            mNewsItemAdapter = NewsItemAdapter(items)
            mBinding?.recyclerview?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mNewsItemAdapter
            }
        }
    }
}
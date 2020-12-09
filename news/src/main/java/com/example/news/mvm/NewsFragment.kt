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
import com.example.news.mvm.picturetitle.NewsPictureTitleModel
import com.example.news.mvm.title.NewsTitleModel

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
        val items = mutableListOf<BaseViewModel>()
        items.add(NewsTitleModel("文字新闻1", ""))
        items.add(NewsPictureTitleModel("文字图片新闻1", "", ""))
        items.add(NewsTitleModel("文字新闻2", ""))
        items.add(NewsPictureTitleModel("文字图片新闻2", "", ""))
        items.add(NewsTitleModel("文字新闻3", ""))
        items.add(NewsPictureTitleModel("文字图片新闻3", "", ""))
        items.add(NewsTitleModel("文字新闻4", ""))
        items.add(NewsPictureTitleModel("文字图片新闻4", "", ""))
        items.add(NewsTitleModel("文字新闻5", ""))
        items.add(NewsPictureTitleModel("文字图片新闻5", "", ""))
        items.add(NewsTitleModel("文字新闻6", ""))
        items.add(NewsPictureTitleModel("文字图片新闻6", "", ""))
        items.add(NewsTitleModel("文字新闻7", ""))
        items.add(NewsPictureTitleModel("文字图片新闻7", "", ""))
        items.add(NewsTitleModel("文字新闻8", ""))
        items.add(NewsPictureTitleModel("文字图片新闻8", "", ""))

        mNewsItemAdapter = NewsItemAdapter(items)
        mBinding?.recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mNewsItemAdapter
        }
    }
}
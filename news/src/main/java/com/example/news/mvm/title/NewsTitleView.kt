package com.example.news.mvm.title

import android.content.Context
import android.view.View
import com.example.base.core.BaseView
import com.example.news.R
import com.example.news.databinding.XNewsTitleItemBinding

class NewsTitleView(context: Context) : BaseView<XNewsTitleItemBinding, NewsTitleModel>(context) {

    override fun getLayoutId(): Int {
        return R.layout.x__news_title_item
    }

    override fun setViewModel2Binding(viewModel: NewsTitleModel) {
        mBinding?.newsTitleModel = viewModel
    }

    override fun onRootClick(view: View?) {

    }
}
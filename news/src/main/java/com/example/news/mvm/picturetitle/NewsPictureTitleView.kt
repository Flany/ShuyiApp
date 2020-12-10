package com.example.news.mvm.picturetitle

import android.content.Context
import android.view.View
import com.example.base.core.BaseView
import com.example.news.R
import com.example.news.databinding.XNewsPictureTitleItemBinding

class NewsPictureTitleView(context: Context) : BaseView<XNewsPictureTitleItemBinding, NewsPictureTitleModel>(context){

    override fun getLayoutId(): Int {
        return R.layout.x__news_picture_title_item
    }

    override fun setViewModel2Binding(viewModel: NewsPictureTitleModel) {
        mBinding?.newsPictureTitleModel = viewModel
    }

    override fun onRootClick(view: View?) {

    }
}
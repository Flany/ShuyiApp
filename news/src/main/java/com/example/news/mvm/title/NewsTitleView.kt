package com.example.news.mvm.title

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.base.core.IBaseView
import com.example.news.R
import com.example.news.databinding.XNewsTitleItemBinding

class NewsTitleView(context: Context) : LinearLayout(context), IBaseView<NewsTitleModel> {

    private var newsTitleModel: NewsTitleModel? = null
    private var mBinding: XNewsTitleItemBinding? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = DataBindingUtil.inflate(inflater, R.layout.x__news_title_item, this, false)
        addView(mBinding?.root)
    }

    override fun setViewModel(viewModel: NewsTitleModel) {
        newsTitleModel = viewModel
        mBinding?.newsTitleModel = viewModel
        mBinding?.executePendingBindings()
    }
}
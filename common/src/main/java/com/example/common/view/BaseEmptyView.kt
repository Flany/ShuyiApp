package com.example.common.view

import android.content.Context
import android.view.View
import com.example.base.core.BaseView
import com.example.base.core.BaseViewModel
import com.example.common.R
import com.example.common.databinding.XEmptyItemBinding

class BaseEmptyView(context: Context) : BaseView<XEmptyItemBinding, BaseViewModel>(context) {

    override fun getLayoutId(): Int {
        return R.layout.x__empty_item
    }

    override fun setViewModel2Binding(viewModel: BaseViewModel) {

    }

    override fun onRootClick(view: View?) {

    }
}
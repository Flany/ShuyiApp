package com.example.common.view

import android.content.Context
import android.view.View
import com.example.base.recyclerview.BaseView
import com.example.base.recyclerview.BaseData
import com.example.common.R
import com.example.common.databinding.XEmptyItemBinding

class BaseEmptyView(context: Context) : BaseView<XEmptyItemBinding, BaseData>(context) {

    override fun getLayoutId(): Int {
        return R.layout.x__empty_item
    }

    override fun setViewModel2Binding(data: BaseData) {

    }

    override fun onRootClick(view: View?, data: BaseData?) {
    }

}
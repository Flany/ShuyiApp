package com.example.common.view

import android.content.Context
import android.view.View
import com.example.base.recyclerview.BaseView
import com.example.base.recyclerview.BaseModel
import com.example.common.R
import com.example.common.databinding.XEmptyItemBinding

class BaseEmptyView(context: Context) : BaseView<XEmptyItemBinding, BaseModel>(context) {

    override fun getLayoutId(): Int {
        return R.layout.x__empty_item
    }

    override fun setViewModel2Binding(model: BaseModel) {

    }

    override fun onRootClick(view: View?, model: BaseModel?) {
    }

}
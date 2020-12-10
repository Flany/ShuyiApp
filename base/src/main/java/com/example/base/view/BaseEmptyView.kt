package com.example.base.view

import android.content.Context
import android.widget.LinearLayout
import com.example.base.core.BaseViewModel
import com.example.base.core.IBaseView

class BaseEmptyView(context: Context) : LinearLayout(context),
    IBaseView<BaseViewModel> {

    override fun setViewModel(viewModel: BaseViewModel) {

    }
}
package com.example.base.core

import android.content.Context
import android.widget.LinearLayout

class BaseEmptyView(context: Context) : LinearLayout(context),
    IBaseView<BaseViewModel> {

    override fun setViewModel(viewModel: BaseViewModel) {

    }
}
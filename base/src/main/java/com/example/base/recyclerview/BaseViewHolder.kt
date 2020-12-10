package com.example.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.base.core.BaseViewModel
import com.example.base.core.IBaseView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: IBaseView<BaseViewModel>? = null

    init {
        view = itemView as IBaseView<BaseViewModel>
    }

    fun bindData(viewModel: BaseViewModel) {
        view?.setViewModel(viewModel)
    }
}
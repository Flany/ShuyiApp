package com.example.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: IBaseView<BaseViewModel>? = null

    init {
        view = itemView as IBaseView<BaseViewModel>
    }

    fun bindData(viewModel: BaseViewModel) {
        view?.setViewModel(viewModel)
    }
}
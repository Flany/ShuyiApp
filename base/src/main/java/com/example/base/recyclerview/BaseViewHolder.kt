package com.example.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: IBaseView<BaseData>? = null

    init {
        view = itemView as IBaseView<BaseData>
    }

    fun bindData(data: BaseData) {
        view?.setViewModel(data)
    }
}
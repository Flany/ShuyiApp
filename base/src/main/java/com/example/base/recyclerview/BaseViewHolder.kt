package com.example.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: IBaseView<BaseModel>? = null

    init {
        view = itemView as IBaseView<BaseModel>
    }

    fun bindData(model: BaseModel) {
        view?.setViewModel(model)
    }
}
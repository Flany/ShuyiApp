package com.example.news.mvm.title

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.view.BaseEmptyView
import com.example.base.recyclerview.BaseViewHolder
import com.example.base.recyclerview.BaseData

class NewsItemAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val items: MutableList<BaseData> = mutableListOf()

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_EMPTY = 1
    }

    fun setData(dataList: MutableList<BaseData>) {
        this.items.clear()
        this.items.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_TITLE -> {
                BaseViewHolder(NewsTitleView(parent.context))
            }
            else -> {
                BaseViewHolder(BaseEmptyView(parent.context))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is NewsTitleData) {
            TYPE_TITLE
        } else {
            TYPE_EMPTY
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(items[position])
    }
}
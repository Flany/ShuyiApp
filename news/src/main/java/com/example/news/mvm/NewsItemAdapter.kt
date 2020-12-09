package com.example.news.mvm

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base.core.BaseEmptyView
import com.example.base.core.BaseViewHolder
import com.example.base.core.BaseViewModel
import com.example.news.mvm.picturetitle.NewsPictureTitleModel
import com.example.news.mvm.picturetitle.NewsPictureTitleView
import com.example.news.mvm.title.NewsTitleModel
import com.example.news.mvm.title.NewsTitleView

class NewsItemAdapter(private val items: MutableList<BaseViewModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_PICTURE_TITLE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_TITLE -> {
                BaseViewHolder(NewsTitleView(parent.context))
            }
            TYPE_PICTURE_TITLE -> {
                BaseViewHolder(NewsPictureTitleView(parent.context))
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
        if (items[position] is NewsTitleModel) {
            return TYPE_TITLE
        } else if (items[position] is NewsPictureTitleModel) {
            return TYPE_PICTURE_TITLE
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(items[position])
    }
}
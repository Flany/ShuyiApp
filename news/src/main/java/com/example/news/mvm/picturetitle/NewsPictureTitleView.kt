package com.example.news.mvm.picturetitle

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.base.core.IBaseView
import com.example.news.R
import com.example.news.databinding.XNewsPictureTitleItemBinding

class NewsPictureTitleView(context: Context) : LinearLayout(context),
    IBaseView<NewsPictureTitleModel> {

    private var newsPictureTitleModel: NewsPictureTitleModel? = null
    private var mBinding: XNewsPictureTitleItemBinding? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.x__news_picture_title_item, this, false)
        addView(mBinding?.root)
    }

    override fun setViewModel(viewModel: NewsPictureTitleModel) {
        newsPictureTitleModel = viewModel
        mBinding?.newsPictureTitleModel = viewModel
        mBinding?.executePendingBindings()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImageUrl")
        fun loadImageUrl(imageView: ImageView, newsPictureUrl: String) {
            Glide.with(imageView.context)
                .load(newsPictureUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}
package com.example.common.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DataBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImageUrl")
        fun loadImageUrl(imageView: ImageView, newsPictureUrl: String) {
            kotlin.runCatching {
                Glide.with(imageView.context)
                    .load(newsPictureUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }
        }
    }
}
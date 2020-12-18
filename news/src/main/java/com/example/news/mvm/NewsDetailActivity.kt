package com.example.news.mvm

import android.content.Context
import android.content.Intent
import com.example.base.BaseActivity
import com.example.news.R
import com.example.news.databinding.XNewsDetailActivityBinding

class NewsDetailActivity : BaseActivity<XNewsDetailActivityBinding>() {

    companion object {

        private const val EXTRA_LINK_URL = "__extra_link_url__"

        fun launch(context: Context, link: String) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_LINK_URL, link)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.x__news_detail_activity
    }

    override fun initViews() {
        intent?.getStringExtra(EXTRA_LINK_URL)?.let {
            mBinding?.webview?.loadUrl(it)
        }
    }

    override fun getStatName(): String {
        return this.javaClass.name
    }
}
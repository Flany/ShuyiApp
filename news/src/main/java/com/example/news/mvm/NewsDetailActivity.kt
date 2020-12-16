package com.example.news.mvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.news.R
import com.example.news.databinding.XNewsDetailActivityBinding

class NewsDetailActivity : AppCompatActivity() {

    private var mBinding: XNewsDetailActivityBinding? = null

    companion object {

        private const val EXTRA_LINK_URL = "__extra_link_url__"

        fun launch(context: Context, link: String) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_LINK_URL, link)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.x__news_detail_activity)
        intent?.getStringExtra(EXTRA_LINK_URL)?.let {
            mBinding?.webview?.loadUrl(it)
        }
    }
}
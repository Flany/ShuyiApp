package com.example.shuyiapp

import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.news.mvm.NewsFragment
import com.example.shuyiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var backPressTime = 0L

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsFragment(), null).commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            ToastUtils.toast("再按一次退出")
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }
}
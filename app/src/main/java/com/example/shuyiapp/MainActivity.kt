package com.example.shuyiapp

import com.example.base.BaseActivity
import com.example.news.mvm.NewsFragment
import com.example.shuyiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

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
}
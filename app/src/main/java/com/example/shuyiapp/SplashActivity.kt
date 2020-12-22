package com.example.shuyiapp

import com.example.base.BaseActivity
import com.example.shuyiapp.databinding.ActivitySplashBinding

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/22
 * @since:  splashActivity
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initViews() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getStatName(): String {
        return javaClass.name
    }
}
package com.example.shuyiapp

import android.content.Context
import androidx.multidex.MultiDex
import com.example.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SyLinkApplication : BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
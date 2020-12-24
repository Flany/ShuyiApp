package com.example.shuyiapp

import android.content.Context
import androidx.multidex.MultiDex
import com.android.scan.ScanConfig
import com.example.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SyLinkApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ScanConfig.instance.initScanConfig()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
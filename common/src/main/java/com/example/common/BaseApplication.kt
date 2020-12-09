package com.example.common

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

open class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ShuyiConfig.isDebug = true
        ShuyiConfig.applicationContext = this
        CrashReport.initCrashReport(applicationContext)
    }
}
package com.example.shuyiapp

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

class ShuyiLinkApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext)
    }
}
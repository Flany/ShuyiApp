package com.example.base

import android.app.Application
import com.example.base.calback.EmptyCallback
import com.example.base.calback.ErrorCallback
import com.example.base.calback.LoadingCallback
import com.kingja.loadsir.core.LoadSir
import com.tencent.bugly.crashreport.CrashReport


open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SyAppConfig.isDebug = true
        SyAppConfig.applicationContext = this
        CrashReport.initCrashReport(applicationContext)

        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
    }
}
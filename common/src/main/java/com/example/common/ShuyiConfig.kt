package com.example.common

import android.app.Application

class ShuyiConfig {

    companion object {
        var appVersionName: String = ""
        var appVersionCode: String = ""
        var isDebug: Boolean = false
        var applicationContext: Application? = null
    }

    fun init(context: Application) {
        applicationContext = context
    }
}
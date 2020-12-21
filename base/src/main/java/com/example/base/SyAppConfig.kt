package com.example.base

import android.app.Application

class SyAppConfig {

    companion object {
        var appVersionName: String = ""
        var appVersionCode: String = ""
        var isDebug: Boolean = false
        var applicationContext: Application? = null
    }
}
package com.example.network.core

import android.app.Application

interface INetworkInfo {
    fun getAppVersionName(): String
    fun getAppVersionCode(): String
    fun isDebug(): Boolean
    fun getApplicationContext(): Application
}
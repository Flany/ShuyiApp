package com.example.base.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.base.SyAppConfig

object ToastUtils {

    fun toast(msg: String) {
        kotlin.runCatching {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Toast.makeText(SyAppConfig.applicationContext, msg, Toast.LENGTH_SHORT).show()
            } else {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(SyAppConfig.applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
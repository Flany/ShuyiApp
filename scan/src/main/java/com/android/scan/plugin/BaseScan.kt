package com.android.scan.plugin

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.android.scan.callback.IScanCallback
import com.example.base.utils.LogUtils

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  扫码的基类，各类设备的扫码需要实现此类
 */
abstract class BaseScan : LifecycleObserver {

    companion object {
        const val TAG = "======scan======="
    }

    protected var scanCallback: IScanCallback? = null

    abstract fun registerScan(context: Context?)

    abstract fun unRegisterScan()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        LogUtils.d(TAG, "扫码-onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        LogUtils.d(TAG, "扫码-onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        unRegisterScan()
    }

    fun addScanCallback(scanCallback: IScanCallback) {
        this.scanCallback = scanCallback
    }
}
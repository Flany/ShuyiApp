package com.android.scan.plugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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

    /**
     * 扫码的广播接收者
     */
    protected val broadcastReceiver: BroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (context == null || intent == null) {
                    return
                }
                onReceiveScanBroadcast(context, intent)
            }
        }
    }

    /**
     * 扫码的回调事件
     */
    protected var scanCallback: IScanCallback? = null

    /**
     * SDK注册
     */
    abstract fun registerScan(context: Context)

    /**
     * SDK注销
     */
    abstract fun unregisterScan()

    /**
     * 开启扫码的广播
     */
    abstract fun startScanBroadcast(context: Context)

    /**
     * 停止扫码的广播
     */
    abstract fun stopScanBroadcast(context: Context)

    /**
     * 注册扫码的广播接收者
     */
    abstract fun registerScanReceiver(context: Context)

    /**
     * 注销扫码的广播接收者
     */
    abstract fun unregisterScanReceiver(context: Context)

    /**
     * 处理扫码的广播的事件
     */
    abstract fun onReceiveScanBroadcast(context: Context, intent: Intent)

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
        unregisterScan()
    }

    /**
     * 添加扫码的回调事件
     */
    fun addScanCallback(scanCallback: IScanCallback) {
        this.scanCallback = scanCallback
    }
}
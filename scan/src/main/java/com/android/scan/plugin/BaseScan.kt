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
                if (intent == null) {
                    return
                }
                onReceiveScanBroadcast(intent)
            }
        }
    }

    /*************************************扫码的回调事件-start***************************************/

    /**
     * 扫码的回调事件
     */
    protected var scanCallback: IScanCallback? = null

    /**
     * 添加扫码的回调事件
     */
    fun addScanCallback(scanCallback: IScanCallback) {
        this.scanCallback = scanCallback
    }

    /**************************************扫码的回调事件-end****************************************/

    /**
     * SDK注册
     */
    abstract fun registerScan()

    /**
     * SDK注销
     */
    abstract fun unregisterScan()

    /************************手机和PAD也可以使用广播的模式来进行扫码界面的打开和数据的传输*******************/
    /********************************第三方PDA，采用广播进行扫码的控制-start****************************/

    /**
     * 开启扫码的广播
     */
    abstract fun startScanBroadcast()

    /**
     * 停止扫码的广播
     */
    abstract fun stopScanBroadcast()

    /**
     * 注册扫码的广播接收者
     */
    abstract fun registerScanReceiver()

    /**
     * 注销扫码的广播接收者
     */
    abstract fun unregisterScanReceiver()

    /**
     * 处理扫码的广播的事件
     */
    abstract fun onReceiveScanBroadcast(intent: Intent)

    /**********************************第三方PDA，采用广播进行扫码的控制-end****************************/

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        LogUtils.d(TAG, "扫码-onStart")
        registerScan()
        registerScanReceiver()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        LogUtils.d(TAG, "扫码-onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        LogUtils.d(TAG, "扫码-onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtils.d(TAG, "扫码-onStop")
        unregisterScanReceiver()
        unregisterScan()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        LogUtils.d(TAG, "扫码-onDestroy")
    }
}
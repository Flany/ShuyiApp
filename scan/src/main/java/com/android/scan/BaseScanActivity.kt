package com.android.scan

import androidx.databinding.ViewDataBinding
import com.android.scan.callback.IScanCallback
import com.android.scan.plugin.BaseScan
import com.android.scan.plugin.ScanConfig
import com.android.scan.plugin.ScanFactory
import com.example.base.BaseActivity

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  扫码的Activity的基类
 */
abstract class BaseScanActivity<V : ViewDataBinding> : BaseActivity<V>(), IScanCallback {

    private val mScan: BaseScan by lazy {
        ScanFactory.create(ScanConfig.scanType)
    }

    fun startScanBroadcastReceiver() {
        mScan.startScanBroadcast()
    }

    fun stopScanBroadcastReceiver() {
        mScan.stopScanBroadcast()
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(mScan)
        mScan.addScanCallback(this)
    }
}
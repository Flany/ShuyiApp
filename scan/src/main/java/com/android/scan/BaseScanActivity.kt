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
 * @since:
 */
abstract class BaseScanActivity<V : ViewDataBinding> : BaseActivity<V>(), IScanCallback {

    private val mScan: BaseScan by lazy {
        ScanFactory.create(ScanConfig.scanType)
    }

    fun startScanBroadcastReceiver() {
        mScan.startScanBroadcast(this)
    }

    fun stopScanBroadcastReceiver() {
        mScan.stopScanBroadcast(this)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(mScan)
        mScan.addScanCallback(this)
    }

    override fun onResume() {
        super.onResume()
        mScan.registerScanReceiver(this)
    }

    override fun onStop() {
        super.onStop()
        mScan.unregisterScanReceiver(this)
    }
}
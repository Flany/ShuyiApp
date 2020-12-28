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

    private var mScan: BaseScan? = null

    override fun onStart() {
        super.onStart()
        mScan = ScanFactory.create(ScanConfig.scanType)
        mScan?.let {
            lifecycle.addObserver(it)
            it.addScanCallback(this)
        }
    }
}
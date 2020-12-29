package com.android.scan

import androidx.databinding.ViewDataBinding
import com.android.scan.callback.IScanCallback
import com.android.scan.plugin.BaseScan
import com.android.scan.plugin.ScanConfig
import com.android.scan.plugin.ScanFactory
import com.example.base.BaseFragment
import com.example.base.vm.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
abstract class BaseScanFragment<VD : ViewDataBinding, VM : BaseViewModel> : BaseFragment<VD, VM>(),
    IScanCallback {

    private val mScan: BaseScan by lazy {
        ScanFactory.create(ScanConfig.scanType)
    }

    fun startScanBroadcastReceiver() {
        mScan.startScanBroadcast(requireContext())
    }

    fun stopScanBroadcastReceiver() {
        mScan.stopScanBroadcast(requireContext())
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(mScan)
        mScan.addScanCallback(this)
    }

    override fun onResume() {
        super.onResume()
        mScan.registerScanReceiver(requireContext())
    }

    override fun onStop() {
        super.onStop()
        mScan.unregisterScanReceiver(requireContext())
    }
}
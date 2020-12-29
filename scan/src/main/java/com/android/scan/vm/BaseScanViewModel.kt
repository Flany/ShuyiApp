package com.android.scan.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.android.scan.callback.IScanCallback
import com.android.scan.data.BaseScanData
import com.android.scan.data.ScanException
import com.android.scan.plugin.BaseScan
import com.android.scan.plugin.ScanConfig
import com.android.scan.plugin.ScanFactory

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
abstract class BaseScanViewModel(val app: Application) : AndroidViewModel(app), LifecycleObserver,
    IScanCallback {

    val scanData = MutableLiveData<BaseScanData>()
    val failure = MutableLiveData<ScanException>()

    val scan: BaseScan by lazy {
        ScanFactory.create(ScanConfig.scanType)
    }

    init {
        scan.addScanCallback(this)
    }

    fun startScan() {
        scan.startScanBroadcast()
    }

    fun stopScan() {
        scan.stopScanBroadcast()
    }

    override fun onScanFailure(throwable: Throwable) {
        failure.postValue(ScanException(throwable.message ?: ""))
    }

    override fun onScanSuccess(data: BaseScanData) {
        scanData.postValue(data)
    }
}
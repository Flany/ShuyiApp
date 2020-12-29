package com.android.scan.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.android.scan.callback.IScanCallback
import com.android.scan.data.BaseScanData
import com.android.scan.data.ScanException

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
class ScanViewModel(val app: Application) : AndroidViewModel(app), LifecycleObserver,
    IScanCallback {

    val scanData = MutableLiveData<BaseScanData>()
    val failure = MutableLiveData<ScanException>()

    override fun onScanFailure(throwable: Throwable) {
        failure.postValue(ScanException(throwable.message ?: ""))
    }

    override fun onScanSuccess(data: BaseScanData) {
        scanData.postValue(data)
    }
}
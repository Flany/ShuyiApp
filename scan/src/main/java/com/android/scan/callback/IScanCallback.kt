package com.android.scan.callback

import com.android.scan.data.BaseScanData

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  扫码的回调接口
 */
interface IScanCallback {

    fun onScanFailure(throwable: Throwable)

    fun onScanSuccess(data: BaseScanData)
}
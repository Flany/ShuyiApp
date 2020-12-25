package com.android.scan.product.mobydata

import android.content.Context
import com.android.scan.data.BaseScanData
import com.android.scan.product.BaseScan
import com.datalogic.decode.*
import com.example.base.utils.LogUtils
import com.google.gson.Gson

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  MobyData的扫码类
 */
class MobyDataScan private constructor() : BaseScan() {

    private var mBarcodeManager: BarcodeManager? = null

    private var readListener: ReadListener? = null
    private var startListener: StartListener? = null
    private var stopListener: StopListener? = null
    private var timeoutListener: TimeoutListener? = null

    companion object {
        val instance: MobyDataScan by lazy {
            MobyDataScan()
        }
    }

    override fun registerScan(context: Context?) {
        try {
            val localBarcodeManager = BarcodeManager()
            localBarcodeManager.isInitialized
            mBarcodeManager = localBarcodeManager
            readListener = ReadListener {
                LogUtils.d(TAG, "scan data -> id: ${it.barcodeID}, text: ${it.text}")
                scanCallback?.onScanSuccess(Gson().fromJson(it.text, BaseScanData::class.java))
            }
            startListener = StartListener {
                LogUtils.d(TAG, "scan is start.")
            }
            stopListener = StopListener {
                LogUtils.d(TAG, "scan is stop.")
            }
            timeoutListener = TimeoutListener {
                LogUtils.d(TAG, "scanning is timeout.")
                scanCallback?.onScanFailure(Throwable("scanning is timeout."))
            }
            mBarcodeManager?.apply {
                addReadListener(readListener)
                addStartListener(startListener)
                addStopListener(stopListener)
                addTimeoutListener(timeoutListener)
            }
        } catch (localException: Exception) {
            LogUtils.d(TAG, "registerScan exception, ${localException.message}")
        }
    }

    override fun unRegisterScan() {
        mBarcodeManager?.apply {
            stopDecode()
            removeReadListener(readListener)
            removeStartListener(startListener)
            removeStopListener(stopListener)
            removeTimeoutListener(timeoutListener)
            release()
            mBarcodeManager = null
        }
    }

    override fun onResume() {

    }

    override fun onPause() {

    }
}
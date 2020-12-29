package com.android.scan.plugin.mobydata

import android.content.Intent
import com.android.scan.data.BaseScanData
import com.android.scan.plugin.BaseScan
import com.datalogic.decode.*
import com.example.base.utils.LogUtils
import com.google.gson.Gson

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  MobyData的扫码类
 */
class MobydataScan private constructor() : BaseScan() {

    private var mBarcodeManager: BarcodeManager? = null

    private var readListener: ReadListener? = null
    private var startListener: StartListener? = null
    private var stopListener: StopListener? = null
    private var timeoutListener: TimeoutListener? = null

    companion object {
        val INSTANCE: MobydataScan by lazy {
            MobydataScan()
        }
    }

    override fun registerScan() {
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

    override fun unregisterScan() {
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

    override fun startScanBroadcast() {

    }

    override fun stopScanBroadcast() {

    }

    override fun registerScanReceiver() {

    }

    override fun unregisterScanReceiver() {

    }

    override fun onReceiveScanBroadcast(intent: Intent) {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }
}
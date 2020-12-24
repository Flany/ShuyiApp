package com.example.base

import androidx.databinding.ViewDataBinding
import com.example.base.utils.LogUtils
import com.example.base.utils.ScanUtil
import com.honeywell.aidc.BarcodeReader
import com.honeywell.aidc.ScannerUnavailableException

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:
 */
abstract class BaseScanActivity<V : ViewDataBinding> : BaseActivity<V>(),
    BarcodeReader.BarcodeListener {

    override fun onResume() {
        super.onResume()
        if (ScanUtil.instance.barcodeReader != null) {
            try {
                ScanUtil.instance.barcodeReader?.claim()
            } catch (e: ScannerUnavailableException) {
                LogUtils.d("BaseScanActivity", "Scanner unavailable")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (ScanUtil.instance.barcodeReader != null) {
            // release the scanner claim so we don't get any scanner
            // notifications while paused.
            ScanUtil.instance.barcodeReader?.release()
        }
    }
}
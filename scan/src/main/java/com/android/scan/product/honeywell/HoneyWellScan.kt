package com.android.scan.product.honeywell

import android.content.Context
import com.android.scan.data.BaseScanData
import com.android.scan.product.BaseScan
import com.example.base.utils.LogUtils
import com.google.gson.Gson
import com.honeywell.aidc.*
import java.util.*

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:
 */
class HoneyWellScan private constructor() : BaseScan(), BarcodeReader.BarcodeListener {

    var barcodeReader: BarcodeReader? = null
    private var manager: AidcManager? = null

    companion object {
        val instance: HoneyWellScan by lazy {
            HoneyWellScan()
        }
    }

    override fun registerScan(context: Context?) {
        if (context == null || (manager != null && barcodeReader != null)) {
            return
        }
        AidcManager.create(context) { manager ->
            this.manager = manager
            try {
                barcodeReader = manager.createBarcodeReader()
                val properties = initProperties()
                // Apply the settings
                // Apply the settings
                barcodeReader?.addBarcodeListener(this)
                barcodeReader?.setProperties(properties)
            } catch (e: InvalidScannerNameException) {
                LogUtils.d(TAG, "e: ${e.message}")
            } catch (e: Exception) {
                LogUtils.d(TAG, "e: ${e.message}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        kotlin.runCatching {
            LogUtils.d(TAG, "onResume==================")
            barcodeReader?.claim()
        }
    }

    override fun onPause() {
        super.onPause()
        kotlin.runCatching {
            LogUtils.d(TAG, "onPause==================")
            barcodeReader?.release()
        }
    }

    override fun unRegisterScan() {
        barcodeReader?.close()
        manager?.close()
    }

    private fun initProperties(): MutableMap<String, Any> {
        val properties: MutableMap<String, Any> =
            HashMap()
        // Set Symbologies On/Off
        // Set Symbologies On/Off
        properties[BarcodeReader.PROPERTY_CODE_128_ENABLED] = true
        properties[BarcodeReader.PROPERTY_GS1_128_ENABLED] = true
        properties[BarcodeReader.PROPERTY_QR_CODE_ENABLED] = true
        properties[BarcodeReader.PROPERTY_CODE_39_ENABLED] = true
        properties[BarcodeReader.PROPERTY_DATAMATRIX_ENABLED] = true
        properties[BarcodeReader.PROPERTY_UPC_A_ENABLE] = true
        properties[BarcodeReader.PROPERTY_EAN_13_ENABLED] = false
        properties[BarcodeReader.PROPERTY_AZTEC_ENABLED] = false
        properties[BarcodeReader.PROPERTY_CODABAR_ENABLED] = false
        properties[BarcodeReader.PROPERTY_INTERLEAVED_25_ENABLED] = false
        properties[BarcodeReader.PROPERTY_PDF_417_ENABLED] = false
        // Set Max Code 39 barcode length
        // Set Max Code 39 barcode length
        properties[BarcodeReader.PROPERTY_CODE_39_MAXIMUM_LENGTH] = 10
        // Turn on center decoding
        // Turn on center decoding
        properties[BarcodeReader.PROPERTY_CENTER_DECODE] = true
        // Enable bad read response
        // Enable bad read response
        properties[BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED] = true
        return properties
    }

    override fun onFailureEvent(p0: BarcodeFailureEvent?) {
        LogUtils.d(TAG, "扫码失败")
        scanCallback?.onScanFailure(Throwable("扫码失败"))
    }

    override fun onBarcodeEvent(p0: BarcodeReadEvent?) {
        val data = p0?.barcodeData
        LogUtils.d(TAG, "扫码成功：$data")
        scanCallback?.onScanSuccess(Gson().fromJson(data, BaseScanData::class.java))
    }
}
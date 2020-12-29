package com.android.scan.plugin.honeywell

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.android.scan.data.BaseScanData
import com.android.scan.plugin.BaseScan
import com.example.base.SyAppConfig
import com.example.base.utils.LogUtils
import com.google.gson.Gson
import com.honeywell.aidc.*
import java.util.*

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:  HoneyWell的扫码类
 */
class HoneywellScan private constructor() : BaseScan(), BarcodeReader.BarcodeListener {

    var barcodeReader: BarcodeReader? = null
    private var manager: AidcManager? = null

    companion object {
        val INSTANCE: HoneywellScan by lazy {
            HoneywellScan()
        }
    }

    override fun registerScan() {
        if (manager != null && barcodeReader != null) {
            return
        }
        AidcManager.create(SyAppConfig.applicationContext) { manager ->
            this.manager = manager
            try {
                barcodeReader = manager.createBarcodeReader()
                val properties = initProperties()
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
            barcodeReader?.claim()
        }
    }

    override fun onPause() {
        super.onPause()
        kotlin.runCatching {
            barcodeReader?.release()
        }
    }

    override fun unregisterScan() {
        kotlin.runCatching {
            barcodeReader?.close()
            manager?.close()
        }
    }

    override fun startScanBroadcast() {
        val bundle = Bundle()
        bundle.putBoolean(HoneywellAction.EXTRA_SCAN, true)
        bundle.putBoolean(HoneywellAction.EXTRA_AIM, true)
        bundle.putBoolean(HoneywellAction.EXTRA_LIGHT, true)
        bundle.putBoolean(HoneywellAction.EXTRA_DECODE, true)
        val intent = Intent(HoneywellAction.ACTION_CONTROL_SCANNER)
        intent.putExtras(bundle)
        SyAppConfig.applicationContext?.sendBroadcast(intent)
    }

    override fun stopScanBroadcast() {
        val bundle = Bundle()
        bundle.putBoolean(HoneywellAction.EXTRA_SCAN, false)
        val intent = Intent(HoneywellAction.ACTION_CONTROL_SCANNER)
        intent.putExtras(bundle)
        SyAppConfig.applicationContext?.sendBroadcast(intent)
    }

    override fun registerScanReceiver() {
        SyAppConfig.applicationContext?.registerReceiver(
            broadcastReceiver,
            IntentFilter(HoneywellAction.ACTION_BARCODE_DATA)
        )
        val properties = Bundle()
        properties.putBoolean("DPR_DATA_INTENT", true)
        properties.putString("DPR_DATA_INTENT_ACTION", HoneywellAction.ACTION_BARCODE_DATA)
        SyAppConfig.applicationContext?.sendBroadcast(
            Intent(HoneywellAction.ACTION_CLAIM_SCANNER)
                .setPackage("com.intermec.datacollectionservice")
                .putExtra(HoneywellAction.EXTRA_SCANNER, "dcs.scanner.imager")
                .putExtra(HoneywellAction.EXTRA_PROFILE, "MyProfile1")
                .putExtra(HoneywellAction.EXTRA_PROPERTIES, properties)
        )
    }

    override fun unregisterScanReceiver() {
        SyAppConfig.applicationContext?.unregisterReceiver(broadcastReceiver)
        SyAppConfig.applicationContext?.sendBroadcast(
            Intent(HoneywellAction.ACTION_RELEASE_SCANNER)
                .setPackage("com.intermec.datacollectionservice")
        )
    }

    override fun onReceiveScanBroadcast(intent: Intent) {
        if (HoneywellAction.ACTION_BARCODE_DATA == intent.action) {
            val version = intent.getIntExtra("version", 0)
            if (version >= 1) {
                val aimId = intent.getStringExtra("aimId")
                val charset = intent.getStringExtra("charset")
                val codeId = intent.getStringExtra("codeId")
                val data = intent.getStringExtra("data")
                val dataBytes = intent.getByteArrayExtra("dataBytes")
                val timestamp = intent.getStringExtra("timestamp")
                LogUtils.d(TAG, "扫码成功-广播：$data")
                scanCallback?.onScanSuccess(Gson().fromJson(data, BaseScanData::class.java))
            }
        }
    }

    private fun initProperties(): MutableMap<String, Any> {
        val properties: MutableMap<String, Any> = HashMap()
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
        LogUtils.d(TAG, "扫码成功-按键：$data")
        scanCallback?.onScanSuccess(Gson().fromJson(data, BaseScanData::class.java))
    }
}
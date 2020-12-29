package com.android.scan.plugin.honeywell

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.android.scan.data.BaseScanData
import com.android.scan.plugin.BaseScan
import com.example.base.SyAppConfig
import com.example.base.utils.LogUtils
import com.google.gson.Gson

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:  HoneyWell的扫码类
 */
class HoneywellScan private constructor() : BaseScan() {

    companion object {
        val INSTANCE: HoneywellScan by lazy {
            HoneywellScan()
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
}
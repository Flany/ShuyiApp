package com.android.scan.plugin.mobydata

import android.content.Intent
import android.content.IntentFilter
import com.android.scan.data.BaseScanData
import com.android.scan.plugin.BaseScan
import com.example.base.SyAppConfig
import com.google.gson.Gson

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  MobyData的扫码类
 */
class MobydataScan private constructor() : BaseScan() {

    companion object {
        val INSTANCE: MobydataScan by lazy {
            MobydataScan()
        }
    }

    override fun startScanBroadcast() {
        val intent = Intent()
        intent.action = MobydataAction.ACTION_START_DECODE
        SyAppConfig.applicationContext?.sendBroadcast(intent)
    }

    override fun stopScanBroadcast() {
        val intent = Intent()
        intent.action = MobydataAction.ACTION_STOP_DECODE
        SyAppConfig.applicationContext?.sendBroadcast(intent)
    }

    override fun registerScanReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(MobydataAction.ACTION_BROADCAST_RECEIVER)
        intentFilter.addCategory(MobydataAction.CATEGORY_BROADCAST_RECEIVER)
        SyAppConfig.applicationContext?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun unregisterScanReceiver() {
        SyAppConfig.applicationContext?.unregisterReceiver(broadcastReceiver)
    }

    override fun onReceiveScanBroadcast(intent: Intent) {
        if (intent.action == MobydataAction.ACTION_BROADCAST_RECEIVER) {
            val data = intent.getStringExtra(MobydataAction.EXTRA_BARCODE_STRING)
            val type = intent.getStringExtra(MobydataAction.EXTRA_BARCODE_TYPE)
            scanCallback?.onScanSuccess(Gson().fromJson(data, BaseScanData::class.java))
        }
    }
}
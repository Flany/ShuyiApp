package com.android.scan.plugin.others

import android.content.Context
import android.content.Intent
import com.android.scan.plugin.BaseScan

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  其他类型的扫码类，主要是没有扫码实体键的其他类型，如pad和手机，调用扫码二维码的功能；有实体扫码键的调用其实现类即可
 */
class OthersScan private constructor() : BaseScan() {

    companion object {
        val instance: OthersScan by lazy {
            OthersScan()
        }
    }

    override fun registerScan(context: Context) {

    }

    override fun unregisterScan() {

    }

    override fun startScanBroadcast(context: Context) {

    }

    override fun stopScanBroadcast(context: Context) {

    }

    override fun registerScanReceiver(context: Context) {

    }

    override fun unregisterScanReceiver(context: Context) {

    }

    override fun onReceiveScanBroadcast(context: Context, intent: Intent) {

    }
}
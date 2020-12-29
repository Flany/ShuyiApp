package com.android.scan.plugin.others

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

    override fun registerScan() {

    }

    override fun unregisterScan() {

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
}
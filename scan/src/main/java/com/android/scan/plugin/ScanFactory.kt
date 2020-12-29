package com.android.scan.plugin

import com.android.scan.plugin.honeywell.HoneyWellScan
import com.android.scan.plugin.mobydata.MobyDataScan
import com.android.scan.plugin.others.OthersScan

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  扫码的工厂类，根据不同的类型，获取不同的扫码工具
 */
object ScanFactory {

    fun create(scanType: ScanType): BaseScan {
        return when (scanType) {
            ScanType.Honeywell -> {
                HoneyWellScan.instance
            }
            ScanType.Mobydata -> {
                MobyDataScan.instance
            }
            ScanType.Others -> {
                OthersScan.instance
            }
        }
    }
}
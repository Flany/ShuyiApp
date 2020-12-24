package com.android.scan.product

import com.android.scan.product.honeywell.HoneyWellScan

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:  扫码的工厂类，根据不同的类型，获取不同的扫码工具
 */
object ScanFactory {

    fun create(scanType: ScanType): BaseScan {
        return when (scanType) {
            ScanType.HONEYWELL -> {
                HoneyWellScan.instance
            }
            ScanType.MOBYDATA -> {
                HoneyWellScan.instance
            }
        }
    }
}
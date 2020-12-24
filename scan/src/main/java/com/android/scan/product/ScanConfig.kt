package com.android.scan.product

import android.os.Build
import java.lang.Exception

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:
 */
class ScanConfig private constructor() {

    companion object {
        var scanType = ScanType.Others

        val instance: ScanConfig by lazy {
            ScanConfig()
        }
    }

    fun initScanConfig() {
        val brand = Build.BRAND
        scanType = try {
            ScanType.valueOf(brand)
        } catch (e: Exception) {
            ScanType.Others
        }
    }
}
package com.android.scan.vm

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
class ScanViewModel(private val scanApp: Application) : BaseScanViewModel(scanApp) {
}
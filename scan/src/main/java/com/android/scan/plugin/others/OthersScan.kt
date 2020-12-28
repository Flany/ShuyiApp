package com.android.scan.plugin.others

import android.content.Context
import com.android.scan.plugin.BaseScan

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/24
 * @since:
 */
class OthersScan private constructor() : BaseScan() {

    companion object {
        val instance: OthersScan by lazy {
            OthersScan()
        }
    }

    override fun registerScan(context: Context?) {

    }

    override fun unRegisterScan() {

    }
}
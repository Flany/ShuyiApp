package com.android.scan.product.others

import android.content.Context
import com.android.scan.product.BaseScan

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
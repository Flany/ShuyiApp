package com.android.scan.plugin.mobydata

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
object MobydataAction {
    const val ACTION_BROADCAST_RECEIVER = "com.android.decodewedge.decode_action"
    const val CATEGORY_BROADCAST_RECEIVER = "com.android.decodewedge.decode_category"

    // extra data
    const val EXTRA_BARCODE_DATA = "com.android.decode.intentwedge.barcode_data"
    const val EXTRA_BARCODE_STRING = "com.android.decode.intentwedge.barcode_string"
    const val EXTRA_BARCODE_TYPE = "com.android.decode.intentwedge.barcode_type"

    const val ACTION_START_DECODE = "com.datalogic.decode.action.START_DECODE"
    const val ACTION_STOP_DECODE = "com.datalogic.decode.action.STOP_DECODE"
}
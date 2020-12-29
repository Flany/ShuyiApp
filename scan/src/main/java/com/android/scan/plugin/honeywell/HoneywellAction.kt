package com.android.scan.plugin.honeywell

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
object HoneywellAction {

    /**
     *
     */
    const val ACTION_BARCODE_DATA = "com.honeywell.sample.action.BARCODE_DATA"

    /**
     * 申明扫码，用于在onResume()中调用
     */
    const val ACTION_CLAIM_SCANNER = "com.honeywell.aidc.action.ACTION_CLAIM_SCANNER"

    /**
     * 控制扫码，用于开启扫码
     * Bundle bundle = new Bundle();
     * bundle.putBoolean("com.honeywell.aidc.extra.EXTRA_SCAN", true);
     * bundle.putBoolean("com.honeywell.aidc.extra.EXTRA_AIM", true);
     * bundle.putBoolean("com.honeywell.aidc.extra.EXTRA_LIGHT", true);
     * bundle.putBoolean("com.honeywell.aidc.extra.EXTRA_DECODE", true);
     * Intent intent = new Intent(ACTION_CONTROL_SCANNER);
     * intent.putExtras(bundle);
     * sendBroadcast(intent);
     */
    const val ACTION_CONTROL_SCANNER = "com.honeywell.aidc.action.ACTION_CONTROL_SCANNER"

    /**
     * 释放扫码资源，用于在onPause()/onStop()中调用
     * sendBroadcast(new Intent(ACTION_RELEASE_SCANNER)
     *      .setPackage("com.intermec.datacollectionservice")
     *);
     */
    const val ACTION_RELEASE_SCANNER = "com.honeywell.aidc.action.ACTION_RELEASE_SCANNER"

    /**
     * 控制扫码参数，true:开启扫码，false:关闭扫码，大多数情况下只需要这个参数
     */
    const val EXTRA_SCAN = "com.honeywell.aidc.extra.EXTRA_SCAN"

    /**
     * 控制扫码参数，指定是否打开扫描仪瞄准器，可选
     */
    const val EXTRA_AIM = "com.honeywell.aidc.extra.EXTRA_AIM"

    /**
     * 控制扫码参数，指定是否打开扫描仪照明打开或关闭，可选
     */
    const val EXTRA_LIGHT = "com.honeywell.aidc.extra.EXTRA_LIGHT"

    /**
     * 控制扫码参数，指定是否打开解码操作开或关，可选
     */
    const val EXTRA_DECODE = "com.honeywell.aidc.extra.EXTRA_DECODE"

    /**
     * 用于设置扫码的参数名称
     * Bundle properties = new Bundle();
     * properties.putBoolean("DPR_DATA_INTENT", true);
     * properties.putString("DPR_DATA_INTENT_ACTION", ACTION_BARCODE_DATA);
     * sendBroadcast(new Intent(ACTION_CLAIM_SCANNER)
     *      .setPackage("com.intermec.datacollectionservice")
     *      .putExtra(EXTRA_SCANNER, "dcs.scanner.imager")
     *      .putExtra(EXTRA_PROFILE, "MyProfile1")
     *      .putExtra(EXTRA_PROPERTIES, properties)
     *);
     */
    const val EXTRA_SCANNER = "com.honeywell.aidc.extra.EXTRA_SCANNER"
    const val EXTRA_PROFILE = "com.honeywell.aidc.extra.EXTRA_PROFILE"
    const val EXTRA_PROPERTIES = "com.honeywell.aidc.extra.EXTRA_PROPERTIES"
}
package com.android.scan

import android.app.Application
import android.content.Context
import android.content.Intent
import com.android.scan.data.BaseScanData
import com.android.scan.databinding.ActivityScanBinding
import com.example.base.utils.ScanUtil
import com.example.base.BaseScanActivity
import com.example.base.utils.LogUtils
import com.example.base.utils.ToastUtils
import com.google.gson.Gson
import com.honeywell.aidc.BarcodeFailureEvent
import com.honeywell.aidc.BarcodeReadEvent
import org.json.JSONObject

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:
 */
class ScanActivity : BaseScanActivity<ActivityScanBinding>() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ScanActivity::class.java)
            if (context is Application) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    override fun initViews() {
        val barcodeReader = ScanUtil.instance.barcodeReader
        if (barcodeReader == null) {
            ToastUtils.toast("扫码还未初始化完成，请稍后")
            return
        }
        ScanUtil.instance.barcodeReader?.addBarcodeListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_scan
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun onFailureEvent(event: BarcodeFailureEvent?) {
        LogUtils.d("扫码失败", "失败原因：${Gson().toJson(event)}")
    }

    override fun onBarcodeEvent(event: BarcodeReadEvent?) {
        val gson = Gson()
        LogUtils.d("扫码成功", "结果数据：${gson.toJson(event)}")
        runOnUiThread {
            event?.barcodeData?.let {
                val data = gson.fromJson(it, BaseScanData::class.java)
                mBinding?.etName?.setText(data.id.toString())
                mBinding?.etDepartment?.setText(data.title)
            }
        }
    }
}
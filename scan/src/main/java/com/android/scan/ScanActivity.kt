package com.android.scan

import android.widget.Toast
import com.android.scan.databinding.ActivityScanBinding
import com.android.scan.utils.ScanUtil
import com.example.base.BaseActivity
import com.example.base.utils.LogUtils
import com.honeywell.aidc.BarcodeFailureEvent
import com.honeywell.aidc.BarcodeReadEvent
import com.honeywell.aidc.BarcodeReader.BarcodeListener
import com.honeywell.aidc.ScannerUnavailableException
import org.json.JSONObject

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:
 */
class ScanActivity : BaseActivity<ActivityScanBinding>(), BarcodeListener {

    override fun initViews() {
        ScanUtil.instance.register(this, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_scan
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun onResume() {
        super.onResume()
        if (ScanUtil.instance.barcodeReader != null) {
            try {
                ScanUtil.instance.barcodeReader?.claim()
            } catch (e: ScannerUnavailableException) {
                e.printStackTrace()
                Toast.makeText(this, "Scanner unavailable", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (ScanUtil.instance.barcodeReader != null) {
            // release the scanner claim so we don't get any scanner
            // notifications while paused.
            ScanUtil.instance.barcodeReader?.release()
        }
    }

    override fun onFailureEvent(event: BarcodeFailureEvent?) {
        LogUtils.d("ScanActivity", event.toString())
    }

    override fun onBarcodeEvent(event: BarcodeReadEvent?) {
        LogUtils.d("ScanActivity", event.toString())
        runOnUiThread {
            event?.barcodeData?.apply {
                val json = JSONObject(this)
                val test = json.optString("test")
                val author = json.optString("author")
                LogUtils.d("ScanActivity", "test:${test}, author:${author}")
                mBinding?.etName?.setText(test)
                mBinding?.etDepartment?.setText(author)
            }
        }
    }
}
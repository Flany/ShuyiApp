package com.android.scan

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.MotionEvent
import com.android.scan.data.BaseScanData
import com.android.scan.databinding.ActivityScanBinding
import com.example.base.utils.LogUtils

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

    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() {
        mBinding?.btnScan?.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startScanBroadcastReceiver()
                }
                MotionEvent.ACTION_UP -> {
                    stopScanBroadcastReceiver()
                }
            }
            true
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_scan
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun onScanFailure(throwable: Throwable) {
        LogUtils.d("扫码失败", "失败原因：${throwable.message}")
    }

    override fun onScanSuccess(data: BaseScanData) {
        runOnUiThread {
            mBinding?.etName?.setText(data.id.toString())
            mBinding?.etDepartment?.setText(data.title)
        }
    }
}
package com.android.scan

import android.annotation.SuppressLint
import android.view.MotionEvent
import com.android.scan.data.BaseScanData
import com.android.scan.data.ScanException
import com.android.scan.databinding.FragmentScanBinding
import com.example.base.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
class ScanFragment : BaseScanFragment<FragmentScanBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() {
        mBinding?.btnScan?.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startScan()
                }
                MotionEvent.ACTION_UP -> {
                    stopScan()
                }
            }
            true
        }
    }

    override fun onReceiverScanData(scanData: BaseScanData) {
        mBinding?.etName?.setText(scanData.id.toString())
        mBinding?.etDepartment?.setText(scanData.title)
    }

    override fun onReceiverScanException(scanException: ScanException) {
        LogUtils.d("扫码失败", "失败原因：${scanException.message}")
    }

    override fun getStatName(): String {
        return ScanFragment::class.java.name
    }
}
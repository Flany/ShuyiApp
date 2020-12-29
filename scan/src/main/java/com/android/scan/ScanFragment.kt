package com.android.scan

import com.android.scan.data.BaseScanData
import com.android.scan.databinding.FragmentScanBinding
import com.android.scan.vm.ScanViewModel
import com.example.base.recyclerview.BaseData
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
class ScanFragment : BaseScanFragment<FragmentScanBinding, ScanViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan
    }

    override fun initViews() {

    }

    override fun getViewModelClass(): Class<ScanViewModel> {
        return ScanViewModel::class.java
    }

    override fun onDataResponded(data: MutableList<BaseData>) {

    }

    override fun onStatusResponded() {

    }

    override fun getStatName(): String {
        return ScanFragment::class.java.name
    }

    override fun onScanFailure(throwable: Throwable) {

    }

    override fun onScanSuccess(data: BaseScanData) {

    }
}
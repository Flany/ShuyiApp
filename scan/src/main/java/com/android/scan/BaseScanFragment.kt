package com.android.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.scan.data.BaseScanData
import com.android.scan.data.ScanException
import com.android.scan.plugin.BaseScan
import com.android.scan.plugin.ScanConfig
import com.android.scan.plugin.ScanFactory
import com.android.scan.vm.ScanViewModel
import com.example.base.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
abstract class BaseScanFragment<VD : ViewDataBinding> : Fragment() {

    protected var mBinding: VD? = null

    private val mViewModel: ScanViewModel by lazy {
        ViewModelProvider(this).get(ScanViewModel::class.java)
    }

    private val mScan: BaseScan by lazy {
        ScanFactory.create(ScanConfig.scanType)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        LogUtils.d(getStatName(), "onCreateView.")
        lifecycle.addObserver(mViewModel)
        lifecycle.addObserver(mScan)
        mScan.addScanCallback(mViewModel)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mViewModel.scanData.observe(viewLifecycleOwner, Observer {
            onReceiverScanData(it)
        })
        mViewModel.failure.observe(viewLifecycleOwner, Observer {
            onReceiverScanException(it)
        })
    }

    abstract fun getLayoutId(): Int

    abstract fun initViews()

    abstract fun onReceiverScanData(scanData: BaseScanData)

    abstract fun onReceiverScanException(scanException: ScanException)

    abstract fun getStatName(): String

    fun startScan() {
        mScan.startScanBroadcast()
    }

    fun stopScan() {
        mScan.stopScanBroadcast()
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(getStatName(), "onStart.")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(getStatName(), "onResume.")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(getStatName(), "onPause.")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(getStatName(), "onStop.")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(getStatName(), "onDestroy.")
    }
}
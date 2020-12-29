package com.android.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.scan.callback.IScanCallback
import com.android.scan.vm.BaseScanViewModel
import com.example.base.recyclerview.BaseData
import com.example.base.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
abstract class BaseScanFragment<VD : ViewDataBinding, VM : BaseScanViewModel> : Fragment(),
    IScanCallback {

    protected var mBinding: VD? = null

    protected val mViewModel: VM by lazy {
        ViewModelProvider(this).get(getViewModelClass())
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        LogUtils.d(getStatName(), "onCreateView.")
        lifecycle.addObserver(mViewModel)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun getLayoutId(): Int

    abstract fun initViews()

    abstract fun getViewModelClass(): Class<VM>

    abstract fun onDataResponded(data: MutableList<BaseData>)

    abstract fun onStatusResponded()

    abstract fun getStatName(): String

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
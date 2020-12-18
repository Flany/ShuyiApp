package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.base.utils.LogUtils

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected var mBinding: V? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        LogUtils.d(getStatName(), "onCreateView.")
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun getLayoutId(): Int

    abstract fun initViews()

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
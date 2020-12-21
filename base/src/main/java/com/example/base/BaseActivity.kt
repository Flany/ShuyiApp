package com.example.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.base.utils.LogUtils

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected var mBinding: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        LogUtils.d(getStatName(), "onCreate.")
        initViews()
    }

    abstract fun initViews()

    abstract fun getLayoutId(): Int

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

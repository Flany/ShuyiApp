package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.calback.ErrorCallback
import com.example.base.calback.LoadingCallback
import com.example.base.recyclerview.BaseData
import com.example.base.state.LoadState
import com.example.base.utils.LogUtils
import com.example.base.vm.BaseViewModel
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseFragment<VD : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected var mBinding: VD? = null
    private var mLoadService: LoadService<View>? = null

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
        mLoadService = LoadSir.getDefault().register(mBinding?.root) {
            mViewModel.loadData()
        } as LoadService<View>?
        lifecycle.addObserver(mViewModel)
        return mLoadService?.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mViewModel.loadState.observe(viewLifecycleOwner, Observer {
            when (it) {
                LoadState.SUCCESS -> {
                    mLoadService?.showSuccess()
                }
                LoadState.LOADING -> {
                    mLoadService?.showCallback(LoadingCallback::class.java)
                }
                LoadState.FAILED -> {
                    mLoadService?.showCallback(ErrorCallback::class.java)
                }
                LoadState.LOAD_MORE_LOADING -> {

                }
                LoadState.LOAD_MORE_FAILED -> {

                }
                else -> {
                }
            }
            onStatusResponded()
        })
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
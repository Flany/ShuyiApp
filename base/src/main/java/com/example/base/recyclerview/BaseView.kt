package com.example.base.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseView<VD : ViewDataBinding, VM : BaseViewModel> :
    LinearLayout, IBaseView<VM> {

    var mBinding: VD? = null
    var mViewModel: VM? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), this, false)
        mBinding?.root?.setOnClickListener {
            onRootClick(it, mViewModel)
        }
        addView(mBinding?.root)
    }

    override fun setViewModel(viewModel: VM) {
        this.mViewModel = viewModel
        setViewModel2Binding(viewModel)
        mBinding?.executePendingBindings()
    }

    abstract fun getLayoutId(): Int

    abstract fun setViewModel2Binding(viewModel: VM)

    abstract fun onRootClick(view: View?, model: VM?)
}
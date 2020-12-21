package com.example.base.calback

import android.content.Context
import android.view.View
import com.example.base.R
import com.kingja.loadsir.callback.Callback

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/18
 * @since:  异常的视图
 */
class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.x__layout_error
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return false
    }
}
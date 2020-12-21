package com.example.base.calback

import com.example.base.R
import com.kingja.loadsir.callback.Callback

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/18
 * @since:  空数据的视图
 */
class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.x__layout_empty
    }
}
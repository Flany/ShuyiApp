package com.example.base.state

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/18
 * @since:  加载的状态
 */
enum class LoadState {
    LOADING,
    SUCCESS,
    FAILED,
    LOAD_MORE_LOADING,
    LOAD_MORE_FAILED
}
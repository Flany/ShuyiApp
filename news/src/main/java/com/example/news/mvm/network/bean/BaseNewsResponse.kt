package com.example.news.mvm.network.bean

open class BaseNewsResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
) {

    companion object {
        const val STATUS_SUCCESS = 0
    }

    fun isSuccess(): Boolean {
        return errorCode == STATUS_SUCCESS
    }
}
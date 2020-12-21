package com.example.base.vm.data

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:
 */
sealed class BaseResult<out T> {
    data class Success<out T>(val value: T) : BaseResult<T>()

    data class Failure(val throwable: Throwable?) : BaseResult<Nothing>()
}

inline fun <reified T> BaseResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is BaseResult.Success) {
        success(value)
    }
}

inline fun <reified T> BaseResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is BaseResult.Failure) {
        failure(throwable)
    }
}
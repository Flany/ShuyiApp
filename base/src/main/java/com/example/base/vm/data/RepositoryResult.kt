package com.example.base.vm.data

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:  repository的结果
 */
sealed class RepositoryResult<out T> {

    data class Success<out T>(val value: T) : RepositoryResult<T>()

    data class Failure(val throwable: Throwable?) : RepositoryResult<Nothing>()
}

inline fun <reified T> RepositoryResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is RepositoryResult.Success) {
        success(value)
    }
}

inline fun <reified T> RepositoryResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is RepositoryResult.Failure) {
        failure(throwable)
    }
}
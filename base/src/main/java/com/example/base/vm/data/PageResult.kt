package com.example.base.vm.data

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:  分页结果集
 */
class PageResult<D>(
    val hasNextPage: Boolean = false,
    val data: MutableList<D>
)
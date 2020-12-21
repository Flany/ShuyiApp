package com.example.base.vm.data

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/21
 * @since:  数据转换接口
 */
interface Mapper<Input, Out> {
    fun map(input: Input): Out
}
package com.android.scan.vm

import com.android.scan.repository.ScanRepository
import com.example.base.vm.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/29
 * @since:
 */
@ExperimentalCoroutinesApi
class ScanViewModel(private val scanRepository: ScanRepository) :
    BaseViewModel(repository = scanRepository) {
}
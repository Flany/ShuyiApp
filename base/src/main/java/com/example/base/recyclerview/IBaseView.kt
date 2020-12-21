package com.example.base.recyclerview

interface IBaseView<VM : BaseData> {
    fun setViewModel(viewModel: VM)
}
package com.example.base.recyclerview

interface IBaseView<VM : BaseViewModel> {
    fun setViewModel(viewModel: VM)
}
package com.example.base.core

interface IBaseView<VM : BaseViewModel> {
    fun setViewModel(viewModel: VM)
}
package com.example.base.core

interface IBaseView<M : BaseViewModel> {
    fun setViewModel(viewModel: M)
}
package com.example.base.recyclerview

interface IBaseView<VM : BaseModel> {
    fun setViewModel(viewModel: VM)
}
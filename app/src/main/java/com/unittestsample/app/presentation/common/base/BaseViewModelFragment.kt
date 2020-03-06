package com.unittestsample.app.presentation.common.base

import android.os.Bundle
import androidx.annotation.CallSuper

abstract class BaseViewModelFragment<T : BaseViewModel> : BaseFragment() {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveDataObservers()
        viewModel.loadPage(isMultipleLoad())
    }

    @CallSuper
    protected open fun initLiveDataObservers() {
    }

    protected open fun isMultipleLoad(): Boolean = false

}

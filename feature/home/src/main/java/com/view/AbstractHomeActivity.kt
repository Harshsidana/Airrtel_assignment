package com.appstreet.home.view

import com.appstreet.base.core.BaseScopeActivity
import com.appstreet.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class AbstractHomeActivity : BaseScopeActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

}
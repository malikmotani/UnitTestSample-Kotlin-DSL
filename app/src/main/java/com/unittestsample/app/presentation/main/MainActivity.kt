package com.unittestsample.app.presentation.main

import androidx.navigation.findNavController
import com.unittestsample.app.R
import com.unittestsample.app.presentation.common.base.BaseActivity

class MainActivity : BaseActivity() {
    protected val navController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun getContentResource() = R.layout.activity_main

    override fun injectDagger() {
        initScreenComponent()?.inject(this)
    }
}

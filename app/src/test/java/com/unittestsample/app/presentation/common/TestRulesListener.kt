package com.unittestsample.app.presentation.common

import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener

object TestRulesListener : TestListener {

    override fun beforeSpec(spec: Spec) {
        instantTaskExecutorRule()
    }
}
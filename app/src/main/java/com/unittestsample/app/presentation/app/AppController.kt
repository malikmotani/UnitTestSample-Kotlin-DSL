package com.unittestsample.app.presentation.app

import android.app.Application
import android.content.Context
import com.unittestsample.app.BuildConfig
import com.unittestsample.app.di.app.AppComponent
import com.unittestsample.app.di.app.AppModule
import com.unittestsample.app.di.app.DaggerAppComponent
import com.unittestsample.app.presentation.common.LocaleHelper
import timber.log.Timber

class AppController : Application() {
    companion object {
        private const val DEFAULT_LANGUAGE = "en"
        lateinit var instance: AppController
            private set
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context?) {
        val newBaseContext = base?.let {
            LocaleHelper.onAttach(
                it,
                DEFAULT_LANGUAGE
            )
        }
        super.attachBaseContext(newBaseContext)
    }

    fun updateLocale(languagePrefix: String) {
        LocaleHelper.onAttach(
            instance,
            DEFAULT_LANGUAGE
        )
    }
}
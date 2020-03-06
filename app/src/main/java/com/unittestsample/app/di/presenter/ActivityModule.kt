package com.unittestsample.app.di.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: FragmentActivity) {

    @Provides
    @ActivityContext
    fun providesContext(): Context = activity

    @Provides
    fun provideActivity(): FragmentActivity = activity
}
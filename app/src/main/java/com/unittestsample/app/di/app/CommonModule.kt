package com.unittestsample.app.di.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
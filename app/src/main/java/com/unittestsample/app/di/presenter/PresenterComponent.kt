package com.unittestsample.app.di.presenter

import com.unittestsample.app.di.app.AppComponent
import com.unittestsample.app.di.viewmodel.ViewModelFactoryModule
import com.unittestsample.app.di.viewmodel.ViewModelModule
import com.unittestsample.app.presentation.main.MainActivity
import com.unittestsample.app.presentation.user.RandomUserFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [PresenterModule::class, ActivityModule::class, ViewModelModule::class, ViewModelFactoryModule::class]
)
@PerPresenter
interface PresenterComponent {
    //injectDagger activity / fragment on here
    fun inject(presenter: MainActivity)
    fun inject(presenter: RandomUserFragment)
}

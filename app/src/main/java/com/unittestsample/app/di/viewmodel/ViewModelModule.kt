package com.unittestsample.app.di.viewmodel

import androidx.lifecycle.ViewModel
import com.unittestsample.app.di.app.CommonModule
import com.unittestsample.app.presentation.user.RandomUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// to Inject ViewModel class
@Module(includes = [CommonModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RandomUserViewModel::class)
    abstract fun bindSignInViewModel(viewModel: RandomUserViewModel): ViewModel
}

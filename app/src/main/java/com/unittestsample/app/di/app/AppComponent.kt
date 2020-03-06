package com.unittestsample.app.di.app

import com.unittestsample.app.di.app.repository.UserModule
import com.unittestsample.app.domain.account.repository.UserRepository
import com.unittestsample.app.presentation.app.AppController
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, NetworkModule::class, UserModule::class, CommonModule::class]
)
@Singleton
interface AppComponent {
    fun inject(app: AppController)
    fun accountRepository(): UserRepository
}
package com.unittestsample.app.di.app.repository

import com.unittestsample.app.data.user.respository.UserApi
import com.unittestsample.app.data.user.respository.UserRepositoryImpl
import com.unittestsample.app.domain.account.repository.UserRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class UserModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: UserApi): UserRepository {
        return UserRepositoryImpl(api)
    }
}
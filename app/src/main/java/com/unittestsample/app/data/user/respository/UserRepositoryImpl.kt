package com.unittestsample.app.data.user.respository

import com.unittestsample.app.data.user.entity.RandomUserApiResponse
import com.unittestsample.app.domain.account.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override fun getRandomUserData(): Single<RandomUserApiResponse> {
        return userApi.randomUser()
    }
}
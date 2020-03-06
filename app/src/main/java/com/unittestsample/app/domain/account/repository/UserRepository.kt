package com.unittestsample.app.domain.account.repository

import com.unittestsample.app.data.user.entity.RandomUserApiResponse
import io.reactivex.Single

interface UserRepository {
    fun getRandomUserData(): Single<RandomUserApiResponse>
}

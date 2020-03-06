package com.unittestsample.app.domain.account.usecase

import com.unittestsample.app.data.user.entity.RandomUserApiResponse
import com.unittestsample.app.domain.account.repository.UserRepository
import com.unittestsample.app.domain.common.ErrorTransformer
import com.unittestsample.app.domain.common.base.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject

class RandomUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    errorTransformer: ErrorTransformer<RandomUserApiResponse>
) : BaseUseCase<RandomUserApiResponse>(errorTransformer) {

    override fun buildSingle(data: Map<String, Any?>): Single<RandomUserApiResponse> {
        return userRepository.getRandomUserData()
    }
}
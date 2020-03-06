package com.unittestsample.app.presentation.user

import com.unittestsample.app.common.extensions.setError
import com.unittestsample.app.common.extensions.setLoading
import com.unittestsample.app.common.extensions.setSuccess
import com.unittestsample.app.domain.account.entity.User
import com.unittestsample.app.domain.account.usecase.RandomUserUseCase
import com.unittestsample.app.presentation.common.Resource
import com.unittestsample.app.presentation.common.base.BaseViewModel
import com.unittestsample.app.presentation.common.base.SingleLiveEvent
import javax.inject.Inject

class RandomUserViewModel @Inject constructor(
    private var randomUserUseCase: RandomUserUseCase
) : BaseViewModel() {

    internal val randomUserLiveEvent = SingleLiveEvent<Resource<ArrayList<User>>>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        fetchRandomUsers()
        return super.loadPage(multipleTimes)
    }

    private fun fetchRandomUsers() {
        randomUserLiveEvent.setLoading()
        randomUserUseCase.execute()
            .subscribe({
                randomUserLiveEvent.setSuccess(it.data)
            }, {
                randomUserLiveEvent.setError(it)
            }).collect()
    }
}
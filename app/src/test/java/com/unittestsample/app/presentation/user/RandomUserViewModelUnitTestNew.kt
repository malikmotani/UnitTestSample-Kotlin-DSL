package com.unittestsample.app.presentation.user

import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import com.unittestsample.app.domain.account.entity.User
import com.unittestsample.app.domain.account.usecase.RandomUserUseCase
import com.unittestsample.app.presentation.common.Resource
import com.unittestsample.app.presentation.common.Status
import com.unittestsample.app.presentation.common.TestRulesListener
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.Single
import org.mockito.Mockito

class RandomUserViewModelUnitTestNew : BehaviorSpec() {
    //output value
    private val successLoginResponse: Resource<ArrayList<User>> =
        Resource(Status.SUCCESS, randomUserResponse().data)
    private val errorLoginResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())

    //mock dependencies
    private val randomUserUseCase = mock<RandomUserUseCase>()
    private val randomUserViewModel by lazy { RandomUserViewModel(randomUserUseCase) }
    private var randomUserObserver = mock<Observer<Resource<ArrayList<User>>>>()

    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        fetchRandomUsersTest()
    }

    private fun fetchRandomUsersTest() {
        Given("Fetch random users") {
            When("Random users data available") {
                stubFetchRandomUserSuccess()
                randomUserViewModel.randomUserLiveEvent.observeForever(randomUserObserver)
                randomUserViewModel.loadPage()
                Then("Fetch random users success") {
                    argumentCaptor<Resource<ArrayList<User>>> {
                        Mockito.verify(randomUserObserver, times(2)).onChanged(capture())
                        firstValue.status shouldBe Status.LOADING
                        secondValue.status shouldBe Status.SUCCESS
                        Gson().toJson(secondValue.data) shouldBe Gson().toJson(successLoginResponse.data)
                    }
                }
            }

            reset(randomUserObserver)

            When("Random users data not available or exception") {
                stubFetchRandomUserError()
                randomUserViewModel.randomUserLiveEvent.observeForever(randomUserObserver)
                randomUserViewModel.loadPage()
                Then("Fetch random users failure") {
                    argumentCaptor<Resource<ArrayList<User>>> {
                        Mockito.verify(randomUserObserver, times(2)).onChanged(capture())
                        firstValue.status shouldBe Status.LOADING
                        secondValue.status shouldBe Status.ERROR
                        Gson().toJson(secondValue.throwable) shouldBe Gson().toJson(errorLoginResponse.throwable)
                    }
                }
            }
        }
    }

    //----------------------------stubbing-------------------------------------//
    private fun stubFetchRandomUserSuccess() {
        whenever(
            randomUserUseCase.execute()
        ).thenReturn(
            Single.just(randomUserResponse())
        )
    }

    private fun stubFetchRandomUserError() {
        whenever(
            randomUserUseCase.execute()
        ).thenReturn(
            Single.error(Throwable())
        )
    }
}
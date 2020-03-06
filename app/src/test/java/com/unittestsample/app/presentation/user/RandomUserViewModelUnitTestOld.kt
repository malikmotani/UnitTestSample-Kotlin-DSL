package com.unittestsample.app.presentation.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import com.unittestsample.app.data.user.entity.RandomUserApiResponse
import com.unittestsample.app.domain.account.entity.User
import com.unittestsample.app.domain.account.usecase.RandomUserUseCase
import com.unittestsample.app.presentation.common.Resource
import com.unittestsample.app.presentation.common.RxImmediateSchedulerRule
import com.unittestsample.app.presentation.common.Status
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class RandomUserViewModelUnitTestOld {
    //output value
    private val successLoginResponse: Resource<ArrayList<User>> =
        Resource(Status.SUCCESS, randomUserResponse().data)
    private val errorLoginResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())
    private val loadingLoginResponse: Resource<ArrayList<User>> = Resource(Status.LOADING)

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    //mock dependencies
    private val randomUserUseCase = mock<RandomUserUseCase>()
    private val randomUserViewModel by lazy { RandomUserViewModel(randomUserUseCase) }
    private var randomUserObserver = mock<Observer<Resource<ArrayList<User>>>>()

    @Captor
    var argumentCaptor: ArgumentCaptor<Resource<ArrayList<User>>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reset(randomUserUseCase)
    }

    @Test
    fun fetchRandomUsers_success() {
        val delayer = PublishSubject.create<Resource<ArrayList<User>>>()

        stub_fetchRandomUser_success(delayer)

        randomUserViewModel.randomUserLiveEvent.observeForever(randomUserObserver)
        randomUserViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(randomUserObserver).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(loadingLoginResponse))
            )
        }
        delayer.onComplete()

        argumentCaptor?.apply {
            Mockito.verify(randomUserObserver, Mockito.times(2)).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(successLoginResponse))
            )
        }
    }

    @Test
    fun fetchRandomUsers_error() {
        stub_fetchRandomUser_error()

        randomUserViewModel.randomUserLiveEvent.observeForever(randomUserObserver)
        randomUserViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(randomUserObserver, Mockito.times(2)).onChanged(capture())

            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(errorLoginResponse))
            )
        }
    }

    //----------------------------stubbing-------------------------------------//

    private fun stub_fetchRandomUser_success(delayer: PublishSubject<Resource<ArrayList<User>>>) {
        whenever(
            randomUserUseCase.execute()
        ).thenReturn(
            Single.create<RandomUserApiResponse> { emitter ->
                try {
                    emitter.onSuccess(randomUserResponse())
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.delaySubscription(delayer)
        )
    }

    private fun stub_fetchRandomUser_error() {
        whenever(
            randomUserUseCase.execute()
        ).thenReturn(
            Single.error(Throwable())
        )
    }
}
package com.unittestsample.app.data.user.respository

import com.unittestsample.app.data.user.entity.RandomUserApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun randomUser(): Single<RandomUserApiResponse>
}
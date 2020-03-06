package com.unittestsample.app.domain.common

import com.google.gson.Gson
import com.unittestsample.app.data.common.entity.BaseResponse
import com.unittestsample.app.data.common.exceptions.AppHttpException
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_BAD_REQUEST
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_FORBIDDEN
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_NOT_FOUND
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_SERVER_ERROR
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_UNAUTHORIZED
import com.unittestsample.app.data.common.exceptions.AppHttpException.Companion.CODE_UNPROCESSABLE
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.inject.Inject

class ErrorTransformer<T> @Inject constructor(private val gson: Gson) : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext {
            val throwable = when (it) {
                is HttpException -> {
                    val body = it.response().errorBody()?.string()
                    val response = gson.fromJson(body, BaseResponse::class.java)
                    val errorBody = response.meta
                    when (it.code()) {
                        CODE_BAD_REQUEST ->
                            AppHttpException.BadRequestException(errorBody)
                        CODE_NOT_FOUND ->
                            AppHttpException.NotFoundException(errorBody)
                        CODE_UNAUTHORIZED ->
                            AppHttpException.UnauthorizedException(errorBody)
                        CODE_FORBIDDEN ->
                            AppHttpException.ForbiddenException(errorBody)
                        CODE_UNPROCESSABLE ->
                            AppHttpException.UnprocessableException(errorBody)
                        CODE_SERVER_ERROR ->
                            AppHttpException.ServerException(errorBody)
                        else -> AppHttpException(errorBody)
                    }
                }
                is UnknownHostException,
                is ConnectException,
                is SocketTimeoutException,
                is UnknownServiceException -> ConnectException()
                else -> it
            }
            Single.error(throwable)
        }
    }

}
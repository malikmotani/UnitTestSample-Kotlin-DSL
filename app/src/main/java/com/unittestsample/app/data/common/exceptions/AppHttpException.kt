package com.unittestsample.app.data.common.exceptions

import com.unittestsample.app.data.common.entity.MetaResponse

open class AppHttpException(val response: MetaResponse) : Exception() {

    companion object {
        const val CODE_BAD_REQUEST = 400
        const val CODE_UNAUTHORIZED = 401
        const val CODE_FORBIDDEN = 403
        const val CODE_NOT_FOUND = 404
        const val CODE_UNPROCESSABLE = 422
        const val CODE_SERVER_ERROR = 500
        const val CODE_REDIRECT = 302
    }

    override val message: String?
        get() = response.message

    class BadRequestException(response: MetaResponse) : AppHttpException(response)
    class NotFoundException(response: MetaResponse) : AppHttpException(response)
    class ForbiddenException(response: MetaResponse) : AppHttpException(response)
    class ServerException(response: MetaResponse) : AppHttpException(response)
    class UnauthorizedException(response: MetaResponse) : AppHttpException(response)
    class UnprocessableException(response: MetaResponse) : AppHttpException(response)
}
package com.unittestsample.app.domain.common.base

import com.unittestsample.app.domain.common.ErrorTransformer
import com.unittestsample.app.domain.common.SchedulerTransformer
import io.reactivex.Single

abstract class BaseUseCase<T>(private val errorTransformer: ErrorTransformer<T>) {

    abstract fun buildSingle(data: Map<String, Any?> = emptyMap()): Single<T>

    fun execute(data: Map<String, Any?> = emptyMap()): Single<T> {
        return buildSingle(data)
            .compose(errorTransformer)
            .compose(SchedulerTransformer())
    }
}

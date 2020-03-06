package com.unittestsample.app.common.extensions

import androidx.lifecycle.MutableLiveData
import com.unittestsample.app.presentation.common.Resource
import com.unittestsample.app.presentation.common.Status

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T){
    postValue(
        Resource(
            Status.SUCCESS,
            data
        )
    )
}

fun <T> MutableLiveData<Resource<T>>.setLoading() = postValue(Resource(Status.LOADING))

fun <T> MutableLiveData<Resource<T>>.setError(throwable: Throwable? = null) {
    postValue(
        Resource(
            Status.ERROR,
            throwable = throwable
        )
    )
}
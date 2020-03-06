package com.unittestsample.app.data.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class BaseResponse {

    @Expose
    @SerializedName("meta")
    lateinit var meta: MetaResponse
}
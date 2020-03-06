package com.unittestsample.app.data.common.entity

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String
)
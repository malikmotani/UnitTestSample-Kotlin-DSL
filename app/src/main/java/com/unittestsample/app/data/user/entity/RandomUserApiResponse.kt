package com.unittestsample.app.data.user.entity

import com.google.gson.annotations.SerializedName
import com.unittestsample.app.domain.account.entity.User

class RandomUserApiResponse {
    @SerializedName("data")
    lateinit var data: ArrayList<User>
}

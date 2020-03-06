package com.unittestsample.app.domain.account.entity

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("avatarUrl")
    lateinit var avatarUrl: String
}
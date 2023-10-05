package com.example.w2_app.data.response

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("login")
    val login: String,


    @field:SerializedName("avatar_url")
    val avatarUrl: String,


)

package com.sana.kotlinwithretrofit

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("website")
    val website: String,

    @SerializedName("login")
    val username: String,

    //The user type is psw
    @SerializedName("type")
    val userType: String,
    //

    @SerializedName("avatar_url")
    val image: String
)
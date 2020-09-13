package com.bgpapp.ui.login

data class LoginResponse(
    val accessToken: String,
    val userInfo: UserInfo
)
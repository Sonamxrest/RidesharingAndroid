package com.example.goodride.Response

import com.example.goodride.model.RegisterModel

data class LoginResponse(
    val success: Boolean? = null,
    val token: String?=null,
    val data: RegisterModel?= null

)
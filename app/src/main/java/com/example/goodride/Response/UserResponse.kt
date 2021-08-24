package com.example.goodride.Response

import com.example.goodride.model.RegisterModel

data class UserResponse (
    var success:Boolean?=null,
            var data:RegisterModel?=null
    )
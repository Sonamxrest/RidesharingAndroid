package com.example.goodride.model

data class RegisterModel(
    val _id : String? = null,
    val FullName: String ? = null,
    val PhoneNumber: String? = null,
    val DateOfBirth: String?=null,
    val Password: String?=null,
    val UserType: String?= null,
    var Latitude: String? = null,
    var Longitude: String? = null
)
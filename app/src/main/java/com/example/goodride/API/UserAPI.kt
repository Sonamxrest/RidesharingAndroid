package com.example.goodride.API

import com.example.goodride.Response.LoginResponse
import com.example.goodride.Response.UserResponse
import com.example.goodride.model.RegisterModel
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    @FormUrlEncoded
    @POST("/userLogin")
    suspend fun LoginUser(
        @Field("PhoneNumber") PhoneNumber:String,
        @Field("Password") Password:String,

    ): Response<LoginResponse>

    @POST("/userRegister")
    suspend fun RegisterUser(
            @Body userDetail: RegisterModel
    ): Response<LoginResponse>

    @GET("/get/{id}")
    suspend fun getUser(@Path("id")id:String):Response<UserResponse>
}
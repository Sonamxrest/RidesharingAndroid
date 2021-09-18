package com.example.goodride.Repository

import com.example.goodride.API.RetrofitService
import com.example.goodride.API.HandelApiRequest
import com.example.goodride.API.UserRoutes
import com.example.goodride.Response.LoginResponse
import com.example.goodride.Response.UserResponse
import com.example.goodride.model.RegisterModel

class UserRepository: HandelApiRequest() {
    val myApi = RetrofitService.buildService(UserRoutes::class.java)

    suspend fun loginUser(PhoneNumber: String, Password: String):LoginResponse{
        return apiRequest {
            myApi.LoginUser(PhoneNumber, Password)
        }
    }

    suspend fun registerUser(userDetail: RegisterModel):LoginResponse{
        return  apiRequest {
            myApi.RegisterUser(userDetail)
        }
    }

    suspend fun getUser(id:String):UserResponse{
        return apiRequest {
            myApi.getUser(id)
        }
    }

}
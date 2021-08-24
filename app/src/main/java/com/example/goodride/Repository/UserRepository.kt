package com.example.goodride.Repository

import com.example.goodride.API.ServiceBuilder
import com.example.goodride.API.TeamApiRequest
import com.example.goodride.API.UserAPI
import com.example.goodride.Registration.Registration
import com.example.goodride.Response.LoginResponse
import com.example.goodride.Response.UserResponse
import com.example.goodride.model.RegisterModel

class UserRepository: TeamApiRequest() {
    val myApi = ServiceBuilder.buildService(UserAPI::class.java)

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
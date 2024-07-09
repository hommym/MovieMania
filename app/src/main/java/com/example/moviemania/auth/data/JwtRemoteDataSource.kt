package com.example.moviemania.auth.data

import com.example.moviemania.auth.component.LoginBody
import com.example.moviemania.auth.component.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JwtRemoteDataSource {


    @POST("auth/login")
    fun getJwtRemotely(@Body data:LoginBody):Call<LoginResponse>


}
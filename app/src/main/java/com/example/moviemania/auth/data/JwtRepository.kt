package com.example.moviemania.auth.data

import android.util.Log
import com.example.moviemania.auth.component.LoginBody

class JwtRepository(private val jwtLocalData:JwtLocalDataSource, private val jwtRemoteData:JwtRemoteDataSource) {




    fun getRemoteJwt(email:String,password:String):String?{
// This method returns a Jwt token when everything is successfully but a null if something goes wrong
        return try {
            //        getting the jwt form backend
            val response= jwtRemoteData.getJwtRemotely(LoginBody(email,password)).execute()

            if(response.isSuccessful){
                response.body()?.token
            } else{
                throw Exception("Request Failed \nReason:${response.message()}")
            }
        } catch (error:Exception){
            Log.d("LogInError","$error")
            null
        }

    }


    suspend fun storeJwt(data:String){

        //store jwt token
        jwtLocalData.storeJwtLocally(data)

    }

     fun getJwtLocal():String?{

        //check local storage for jwt
        val jwtToken:String?=jwtLocalData.getJwtLocally()

        return jwtToken
    }


}
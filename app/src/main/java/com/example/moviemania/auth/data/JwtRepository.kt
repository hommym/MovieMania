package com.example.moviemania.auth.data

import android.util.Log
import com.example.moviemania.auth.component.LoginBody
import com.example.moviemania.components.ApiError

class JwtRepository(private val jwtLocalData:JwtLocalDataSource, private val jwtRemoteData:JwtRemoteDataSource) {




    fun getRemoteJwt(email:String,password:String):String?{
// This method returns a Jwt token when everything is successfully , a null if there is network error
//  and throws an error if the request was not successfully

        return try {
            //        getting the jwt form backend
            val response= jwtRemoteData.getJwtRemotely(LoginBody(email,password)).execute()

            if(response.isSuccessful){
                response.body()?.token
            } else{

                Log.d("LogInError","${response.code()}")
                throw (ApiError(response.errorBody()).extractMessage())
            }
        } catch (error:Exception){
            Log.d("LogInError","${error.message}")
            if(error is ApiError){
                throw Exception(error.message)
            }
            else{
                null
            }
        }

    }


    suspend fun storeJwt(data:String){

        //store jwt token
        jwtLocalData.storeJwtLocally(data)

    }

  suspend   fun getJwtLocal():String?{

        //check local storage for jwt
        val jwtToken:String?=jwtLocalData.getJwtLocally()

        return jwtToken
    }


}
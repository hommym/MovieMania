package com.example.moviemania.auth.data

class JwtRepository(jwtLocalData:JwtLocalDataSource) {




    fun storeJwt(){

//store jwt token

    }

    fun getJwtLocal():String?{

//        check local storage for jwt
        val jwtToken:String?=null


        return jwtToken
    }


}
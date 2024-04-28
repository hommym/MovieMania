package com.example.moviemania.auth.data

class JwtRepository( val jwtLocalData:JwtLocalDataSource) {




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
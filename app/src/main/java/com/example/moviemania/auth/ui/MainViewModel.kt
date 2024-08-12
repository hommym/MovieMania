package com.example.moviemania.auth.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.moviemania.auth.data.JwtRepository
import com.example.moviemania.components.BaseViewModel
import com.example.moviemania.components.FragmentNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel():ViewModel(),BaseViewModel {


 private var jwtRepo:JwtRepository?=null

//    only set this property when using it in an activity
 override   var fragmentNavigator:FragmentNavigator?=null

   suspend fun storeJwtLocally(token:String){
        Log.d("Saving Jwt locally","Token=$token")
        jwtRepo?.storeJwt(token)
    }
 suspend fun checkJwtLocally():String?{

//    checking jwt locally (not implemented)
    val jwtToken:String?=jwtRepo?.getJwtLocal()

    return jwtToken
}

 suspend fun getJwtRemotely(email:String,password:String):String?{
   return  withContext(Dispatchers.IO){
         jwtRepo?.getRemoteJwt(email,password)
     }
    }



fun checkJwtValidity():String?{

//    checking jwt token (not implemented)
    val jwtToken:String?=null


    return jwtToken
}



fun setJwtRepoInstance(repoInstance:JwtRepository){
    jwtRepo=repoInstance
}



}
package com.example.moviemania.auth.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.moviemania.auth.data.JwtRepository

class MainViewModel():ViewModel() {

 private var fragmentInstance: Fragment?=null
 private var jwtRepo:JwtRepository?=null
 fun checkJwtLocally():String?{

//    checking jwt locally (not implemented)
    val jwtToken:String?=jwtRepo?.getJwtLocal()

    return jwtToken
}

fun checkJwtValidity():String?{

//    checking jwt token (not implemented)
    val jwtToken:String?=null


    return jwtToken
}

fun getFragInstance():Fragment?{

    return fragmentInstance
}
fun setFragmentInstance(currentFragment:Fragment){

    fragmentInstance=currentFragment

}

fun setJwtRepoInstance(repoInstance:JwtRepository){
    jwtRepo=repoInstance
}



}
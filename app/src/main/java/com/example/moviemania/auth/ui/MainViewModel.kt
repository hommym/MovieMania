package com.example.moviemania.auth.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.moviemania.auth.data.JwtRepository
import com.example.moviemania.components.BaseViewModel

class MainViewModel():ViewModel(),BaseViewModel {

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

override fun getFragInstance():Fragment?{

    return fragmentInstance
}
override fun setFragmentInstance(currentFragment:Fragment){

    fragmentInstance=currentFragment

}

fun setJwtRepoInstance(repoInstance:JwtRepository){
    jwtRepo=repoInstance
}



}
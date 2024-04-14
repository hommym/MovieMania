package com.example.moviemania.auth.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

class MainViewModel():ViewModel() {

 private var fragmentInstance: Fragment?=null

fun checkJwtLocally():String?{

//    checking jwt locally (not implemented)
    val jwtToken:String?=null

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



}
package com.example.moviemania.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemania.components.BaseViewModel
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.components.customDataTypes.HomeFragmentData

class HomeActivityViewModel():ViewModel(),BaseViewModel {

    override var fragmentNavigator: FragmentNavigator?=null
    var currentTab:Int?=null
    var jwtToken:String?=null

    var homeFragmentData=HomeFragmentData()
    val homeFragmentLiveData=MutableLiveData(homeFragmentData)


}
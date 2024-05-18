package com.example.moviemania.home.ui

import androidx.lifecycle.ViewModel
import com.example.moviemania.components.BaseViewModel
import com.example.moviemania.components.FragmentNavigator

class HomeActivityViewModel():ViewModel(),BaseViewModel {

    override var fragmentNavigator: FragmentNavigator?=null
    var currentTab:Int?=null



}
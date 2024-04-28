package com.example.moviemania.components

import androidx.fragment.app.Fragment

interface BaseViewModel {


    fun getFragInstance(): Fragment?

    fun setFragmentInstance(currentFragment:Fragment)

}
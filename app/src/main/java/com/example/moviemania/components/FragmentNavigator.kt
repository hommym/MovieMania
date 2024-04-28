package com.example.moviemania.components

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.moviemania.R
import com.example.moviemania.auth.ui.LoginFrag

class FragmentNavigator(
    private val fragmentManager:FragmentManager, private val nextFragment:Fragment,
    private val layoutId:Int, private val  viewModel:BaseViewModel,var fragTag:String?=null) {


    private val fragTransaction=fragmentManager.beginTransaction()

    companion object{
        fun back(fragmentManager:FragmentManager){
            fragmentManager.popBackStack()
        }
    }

    fun addFragment(){


        if ( fragmentManager.findFragmentById(layoutId)==null){
            fragTransaction.add(layoutId,nextFragment)
            fragTransaction.addToBackStack(fragTag)
            fragTransaction.commit()
        }



    }


    fun replaceFragment(){
//                saving fragment instance
        fragTransaction.replace(layoutId,nextFragment)
        fragTransaction.addToBackStack(fragTag)
        fragTransaction.commit()
    }





}
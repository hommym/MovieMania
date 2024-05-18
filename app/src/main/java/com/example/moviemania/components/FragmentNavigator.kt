package com.example.moviemania.components


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager



//This is the component is used to control all fragment addition and replacement in an Activity by passing
//by storing it instance in the view-model of that activity
class FragmentNavigator(
     var fragmentManager:FragmentManager, private val nextFragment:Fragment,
    private val layoutId:Int,private var fragTag:String?=null,private var addToBackstack:Boolean=true) {




    companion object{
        fun back(fragmentManager:FragmentManager){
            fragmentManager.popBackStack()
        }
    }

    fun addFragment(){


        if ( fragmentManager.findFragmentById(layoutId)==null){
            val fragTransaction=fragmentManager.beginTransaction()
            fragTransaction.add(layoutId,nextFragment)
            if(addToBackstack){
                fragTransaction.addToBackStack(fragTag)
            }
            fragTransaction.commit()
        }



    }


    fun replaceFragment(fragmentInstance:Fragment){
//               replacing fragment
        val fragTransaction=fragmentManager.beginTransaction()
        fragTransaction.replace(layoutId,fragmentInstance)
        if(addToBackstack){
            fragTransaction.addToBackStack(fragTag)
        }
        fragTransaction.commit()
    }





}
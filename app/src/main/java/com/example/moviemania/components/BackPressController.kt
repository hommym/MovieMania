package com.example.moviemania.components

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

//used in activities to control what happens when back button is press
class BackPressController(val context:Context,private val layoutIdForFragments:Int) {


    fun backPressListener(){

       (context as AppCompatActivity).onBackPressedDispatcher.addCallback(context,object :
         OnBackPressedCallback(true) {
         override fun handleOnBackPressed() {
                  val fragmentManager=context.supportFragmentManager

             if(fragmentManager.backStackEntryCount==1){
                 context.finish()
             }
             else{
                 fragmentManager.popBackStack()
             }

         }

     })

    }

}
package com.example.moviemania.auth.component

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.delay

class LayoutAnimator(val layout:ViewGroup) {

   fun settingUpAnimationConfigs(){

//        setting up aniamtions for items in layout
      val layoutTransObj= LayoutTransition()
      for(childViewPosition in 0 until layout.childCount){

         layoutTransObj.setAnimator(
            LayoutTransition.APPEARING,
            ObjectAnimator.ofFloat(layout.getChildAt(childViewPosition),"translationX",400f,0f))
      }

      layout.layoutTransition=layoutTransObj

   }


   suspend fun startAnimation(){
      delay(300)
      for(childViewPosition in 0 until layout.childCount){
         layout.getChildAt(childViewPosition).visibility=View.VISIBLE
         delay(200)
      }


   }


}
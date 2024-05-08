package com.example.moviemania.auth.component

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.coroutines.delay

class LayoutAnimator(val layout:ViewGroup,val context:Context) {

   val layoutTransObj= LayoutTransition()


   fun settingUpAnimationConfigs(){

//        setting up aniamtions for items in layout
      val childView=layout.getChildAt(0)

//      for appearing views
      layoutTransObj.setAnimator(
         LayoutTransition.APPEARING,
         ObjectAnimator.ofFloat(childView,"translationX",400f,0f))

//      for disappearing views
      layoutTransObj.setAnimator(
         LayoutTransition.DISAPPEARING,
         ObjectAnimator.ofFloat(childView,"translationX",0f,-400f))

      layout.layoutTransition=layoutTransObj

   }


   suspend fun startEnteranceAnimationForViews(viewsToIgnore:View?=null){
      delay(300)
      for(childViewPosition in 0 until layout.childCount){
         val childView=layout.getChildAt(childViewPosition)
         if(childView.id==viewsToIgnore?.id){
            continue
         }
         childView.visibility=View.VISIBLE
         delay(200)
      }




   }


   suspend fun startButtonClickAnimation(button:Button,loadingSpinner:ProgressBar){

//      changing appearing animation for the layout
      layoutTransObj.setAnimator(
         LayoutTransition.APPEARING,
         ObjectAnimator.ofFloat(loadingSpinner,"translationY",-50f,0f))

      button.visibility=View.INVISIBLE
      delay(200)
      loadingSpinner.visibility=View.VISIBLE
   }




}
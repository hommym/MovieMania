package com.example.moviemania.video.ui.movie


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.ui.PlayerView
import com.example.moviemania.video.libs.ExoplayerConfig

class MovieViewModel():ViewModel() {






    fun startVideo(playerView: PlayerView,  context: Context){

//        call method for getting movie url here  NYI
        val videoUrl="https://mlauahr4tc.b34zobxzxs73nkfxike1.cfd/res/614774a84bca32182e1b81d831542d9a/d9af8ce2bbeb97c0f383771921bdcaa0/Godzilla_x_Kong_The_New_Empire_(2024)_CAMRip_high_(fzmovies.net)_477831242f6f0e6730d6acebeb869508.mp4?fromwebsite"
        ExoplayerConfig(videoUrl,playerView,context).preparePlayer().startVideo()
    }


}
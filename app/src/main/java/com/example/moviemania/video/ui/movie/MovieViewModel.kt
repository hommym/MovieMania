package com.example.moviemania.video.ui.movie


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.ui.PlayerView
import com.example.moviemania.video.libs.ExoplayerConfig

class MovieViewModel():ViewModel() {






    fun startVideo(playerView: PlayerView,  context: Context){

//        call method for getting movie url here  NYI
        val videoUrl="https://stagatvfiles.com/videos/uploads/2022/01/Peacemaker-S01E01-STAGATV-COM.mp4"
        ExoplayerConfig(videoUrl,playerView,context).preparePlayer().startVideo()
    }


}
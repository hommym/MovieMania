package com.example.moviemania.video.libs

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class ExoplayerConfig(private val videoUrl:String, private val playerView: PlayerView, private val context:Context) {

    //creating exo player instance
    private val player = ExoPlayer.Builder(context).build()
    fun preparePlayer():ExoplayerConfig{
        // Bind the player to the view.
        playerView.player = player

        // Build the media item.
        Log.d("CheckLink",videoUrl)
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
       // Set the media item to be played.
        player.setMediaItem(mediaItem)
       // Prepare the player
        player.prepare()
        return this
    }

    fun preparePlayerForPlaylist(){
//        not yet implemented
    }


    fun startVideo(){
        player.play()
    }




}
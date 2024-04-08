package com.example.moviemania.video.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.moviemania.R
import com.example.moviemania.databinding.ActivityVideoBinding
import com.example.moviemania.video.VideoViewModel
import com.example.moviemania.video.ui.movie.MovieFragment

//this activity must be started with data passed into the key:VideoType
class VideoActivity : AppCompatActivity() {

    lateinit var views:ActivityVideoBinding
    private val uiStateHolder:VideoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         views= ActivityVideoBinding.inflate(layoutInflater)
        setContentView(views.root)


    }


    override fun onResume() {
        super.onResume()

//checking if this callback is not as a result of activity restart
    if(!uiStateHolder.isActivityRestarting){

        val videoType=intent.getStringExtra("VideoType")

        if(videoType=="movie"){
            val fragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.videoLayout,MovieFragment())
            fragmentTransaction.commit()
            Toast.makeText(this,"VidAct",Toast.LENGTH_LONG).show()
        }
        else if(videoType=="series"){
//            NYI
            println("series")
        }

        else{
//            NYI
            println("youtube")
        }



        uiStateHolder.isActivityRestarting=true
    }




    }
}
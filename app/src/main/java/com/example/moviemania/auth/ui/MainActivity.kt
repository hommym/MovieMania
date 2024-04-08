package com.example.moviemania.auth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviemania.R
import com.example.moviemania.video.ui.VideoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }


    override fun onResume() {
        super.onResume()

        val intentObj= Intent(this@MainActivity,VideoActivity::class.java)
//        for now VideoType will only be movie
        intentObj.putExtra("VideoType","movie")
        startActivity(intentObj)
    }
}
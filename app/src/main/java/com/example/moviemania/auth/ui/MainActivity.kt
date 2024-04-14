package com.example.moviemania.auth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviemania.R
import com.example.moviemania.video.ui.VideoActivity

class MainActivity : AppCompatActivity() {


    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }


    override fun onResume() {
        super.onResume()

//        val intentObj= Intent(this@MainActivity,VideoActivity::class.java)
////        for now VideoType will only be movie
//        intentObj.putExtra("VideoType","movie")
//        startActivity(intentObj)



        if(viewModel.checkJwtLocally()!=null){
//            check the validity of jwt

        }
        else{
            val fragTransaction=supportFragmentManager.beginTransaction()


            if(viewModel.getFragInstance()!=null){
                fragTransaction.add(R.id.main_layout,viewModel.getFragInstance()!!)
            }
            else{

                val fragInstance=LoginFrag()

//                saving fragment instance
                viewModel.setFragmentInstance(fragInstance)
                fragTransaction.add(R.id.main_layout,fragInstance)
            }


          fragTransaction.commit()
        }





    }
}
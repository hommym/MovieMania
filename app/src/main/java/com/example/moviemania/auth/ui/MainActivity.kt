package com.example.moviemania.auth.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.R
import com.example.moviemania.auth.data.JwtLocalDataSource
import com.example.moviemania.auth.data.JwtRepository
import com.example.moviemania.video.ui.VideoActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private  val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="UserJwtToken")
    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// creating instance of jwtRepo
        viewModel.setJwtRepoInstance(JwtRepository((JwtLocalDataSource(dataStore))))


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
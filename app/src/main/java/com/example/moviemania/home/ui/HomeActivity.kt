package com.example.moviemania.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviemania.R
import com.example.moviemania.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    lateinit var views:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(views.root)
    }


    override fun onResume() {
        super.onResume()

        views.bottomNavigation.setOnItemSelectedListener {menuItem->

//            changing the colour when an item is selecting


           if(menuItem.title==""){

             true
           }
            else{
              true
           }
        }
    }
}
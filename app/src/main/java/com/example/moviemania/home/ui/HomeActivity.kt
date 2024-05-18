package com.example.moviemania.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.moviemania.R
import com.example.moviemania.components.BackPressController
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    lateinit var views:ActivityHomeBinding
    val viewModel:HomeActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views=ActivityHomeBinding.inflate(layoutInflater)

        //setting up backPress controller
        BackPressController(this@HomeActivity).backPressListener()

        setContentView(views.root)
    }

    override fun onStart() {
        super.onStart()
        //                       setting up fragment navigator component for this activity
        if(viewModel.fragmentNavigator==null){
            viewModel.fragmentNavigator=  FragmentNavigator(supportFragmentManager,HomeFragment(),
                R.id.layoutForHomeFragments, addToBackstack = false)
        }
        //  resetting fragmentManager during configuration  changes
        viewModel.fragmentNavigator?.fragmentManager=supportFragmentManager


        views.bottomNavigation.setOnItemSelectedListener {menuItem->

//            changing the colour when an item is selecting

            when(menuItem.itemId){

                R.id.home_tab->{

                    viewModel.fragmentNavigator?.addFragment()
                    if(supportFragmentManager.findFragmentById(R.id.layoutForHomeFragments)!=null){
                        viewModel.fragmentNavigator?.replaceFragment(HomeFragment())
                    }



                    true
                }

                R.id.movies_tab->{
                    viewModel.fragmentNavigator?.replaceFragment(MoviesFragment())
                    true
                }

                R.id.tv_series_tab->{
                    viewModel.fragmentNavigator?.replaceFragment(SeriesFragment())
                    true
                }

                R.id.watch_list_tab->{
                    viewModel.fragmentNavigator?.replaceFragment(WatchLaterFragment())
                    true
                }


                else -> {
                    viewModel.fragmentNavigator?.replaceFragment(MeFragment())
                    true
                }
            }
        }

        //        selecting the home tab of bottom navBar by default
        views.bottomNavigation.selectedItemId=R.id.home_tab

        views.bottomNavigation.setOnItemReselectedListener {
            Toast.makeText(this@HomeActivity,"I am",Toast.LENGTH_SHORT).show()
        }


    }



}
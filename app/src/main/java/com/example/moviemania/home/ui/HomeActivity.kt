package com.example.moviemania.home.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.R
import com.example.moviemania.auth.ui.MainActivity
import com.example.moviemania.components.AppConfigurations
import com.example.moviemania.components.BackPressController
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.components.libs.RetrofitConfigs
import com.example.moviemania.databinding.ActivityHomeBinding
import com.example.moviemania.home.data.HomeTabDataRepository
import com.example.moviemania.home.data.HomeTabMovieRemoteData
import com.example.moviemania.home.data.HomeTabSeriesRemoteData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import retrofit2.HttpException
import java.io.IOException

class HomeActivity : AppCompatActivity() {


    private lateinit var views:ActivityHomeBinding
    private val viewModel:HomeActivityViewModel by viewModels()
    private val retrofitForMovies= RetrofitConfigs("${AppConfigurations.baseUrlBackend}movie/")
    private val retrofitForSeries=RetrofitConfigs("${AppConfigurations.baseUrlBackend}series/")
    private val homeTabDataRepository=HomeTabDataRepository(retrofitForMovies.config(HomeTabMovieRemoteData::class.java)
    ,retrofitForSeries.config(HomeTabSeriesRemoteData::class.java))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views=ActivityHomeBinding.inflate(layoutInflater)

        //setting up backPress controller
        BackPressController(this@HomeActivity).backPressListener()

        //        setting up jwtToken
        viewModel.jwtToken= intent.getStringExtra("jwtToken")

        // setting up home tab live data
        lifecycleScope.launch(Dispatchers.Main) {

            viewModel.homeFragmentLiveData.observe(this@HomeActivity, Observer {
                viewModel.homeFragmentData.apply {
                    if (recentMovies!=null&&popularMovies!=null&&trendingMovies!=null){
                        // making the loading spinner disappeared
                        views.progressBar.visibility=View.GONE
                        views.bottomNavigation.visibility= View.VISIBLE



                    }
                }

            })


        }


        setContentView(views.root)
    }

    override fun onStart() {
        super.onStart()


        views.bottomNavigation.setOnItemSelectedListener {menuItem->

//            changing the colour when an item is selecting

            when(menuItem.itemId){

                R.id.home_tab->{
                    viewModel.currentTab=R.id.home_tab
                    if(supportFragmentManager.findFragmentById(R.id.layoutForHomeFragments)!=null){
                        viewModel.fragmentNavigator?.replaceFragment(HomeFragment())
                    }
                    else{
                        viewModel.fragmentNavigator?.addFragment()
                    }



                    true
                }

                R.id.movies_tab->{
                    viewModel.currentTab=R.id.movies_tab
                    viewModel.fragmentNavigator?.replaceFragment(MoviesFragment())
                    true
                }

                R.id.tv_series_tab->{
                    viewModel.currentTab=R.id.tv_series_tab
                    viewModel.fragmentNavigator?.replaceFragment(SeriesFragment())
                    true
                }

                R.id.watch_list_tab->{
                    viewModel.currentTab=R.id.watch_list_tab
                    viewModel.fragmentNavigator?.replaceFragment(WatchLaterFragment())
                    true
                }


                else -> {
                    viewModel.currentTab=R.id.me_tab
                    viewModel.fragmentNavigator?.replaceFragment(MeFragment())
                    true
                }
            }
        }

//         updating the data in the liveData during configuration changes
        viewModel.homeFragmentLiveData.value=viewModel.homeFragmentData

        if(viewModel.fragmentNavigator==null){
        val errorHandler= CoroutineExceptionHandler { _, throwable ->
            // making the loading spinner disappeared
            views.progressBar.visibility=View.INVISIBLE

            // making the error icon appear
            if(throwable is IOException){
//                Toast message is temporary
                Toast.makeText(this@HomeActivity,"Network Error",Toast.LENGTH_SHORT).show()
            }
            else if(throwable is HttpException){
                if(throwable.code()==401){
//                    moving to main activity since jwt has expired
                    val intent= Intent(this@HomeActivity,MainActivity::class.java)
                    intent.putExtra("jwtExpired",true)
                    startActivity(intent)
                    finish()
                }else{
                    //                Toast message is temporary
                    Toast.makeText(this@HomeActivity,"Server Error",Toast.LENGTH_SHORT).show()
                }

            }



        }

        lifecycleScope.launch(Dispatchers.Main+errorHandler){
            supervisorScope {
                //making the request for the home tab data and saving the returned data
                // in homeFragmentData in the viewModel
                homeTabDataRepository.populateTabWithData(viewModel.homeFragmentData,
                "Bearer ${viewModel.jwtToken}")
            }
            //                       setting up fragment navigator component for this activity
            viewModel.fragmentNavigator=  FragmentNavigator(supportFragmentManager,HomeFragment(),
                R.id.layoutForHomeFragments, addToBackstack = false)
            // updating the data in the liveData
            viewModel.homeFragmentLiveData.value=viewModel.homeFragmentData

            //            setting the default tab when activity starts for the first time
            viewModel.currentTab=R.id.home_tab

            //        selecting the home tab of bottom navBar by default
            views.bottomNavigation.selectedItemId=viewModel.currentTab!!
        }

        }

        //  resetting fragmentManager during configuration  changes
        viewModel.fragmentNavigator?.fragmentManager=supportFragmentManager





    }



}
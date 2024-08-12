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
import com.example.moviemania.auth.component.LoginResponse
import com.example.moviemania.auth.data.JwtLocalDataSource
import com.example.moviemania.auth.data.JwtRemoteDataSource
import com.example.moviemania.auth.data.JwtRepository
import com.example.moviemania.components.App
import com.example.moviemania.components.AppConfigurations
import com.example.moviemania.components.BackPressController
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.components.libs.RetrofitConfigs
import com.example.moviemania.home.ui.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

//    private  val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="UserJwtToken")
private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // creating instance of jwtRepo
        viewModel.setJwtRepoInstance(JwtRepository((JwtLocalDataSource((application as App).dataStoreI!!))
        ,RetrofitConfigs(AppConfigurations.baseUrlBackend).config(JwtRemoteDataSource::class.java)))


        // checking if jwt has expired
        if(intent.getBooleanExtra("jwtExpired",false)){
            //setting jwt storage location to empty since it has expired
            lifecycleScope.launch {
                viewModel.storeJwtLocally("")
            }
        }

        //setting up backPress controller
        BackPressController(this@MainActivity).backPressListener()

    }


    override fun onStart() {
        super.onStart()
        if(viewModel.fragmentNavigator==null){
            viewModel.fragmentNavigator=FragmentNavigator(supportFragmentManager,LoginFrag(),
                R.id.main_layout,"LoginFrag")
        }
        //  resetting fragmentManager during configuration  changes
        viewModel.fragmentNavigator?.fragmentManager=supportFragmentManager
        lifecycleScope.launch(Dispatchers.Main) {
        if(viewModel.checkJwtLocally()!=null&&viewModel.checkJwtLocally()!=""){
            //                moving to home activity
            val intent=Intent(this@MainActivity, HomeActivity::class.java)
            intent.putExtra("jwtToken",viewModel.checkJwtLocally())
            startActivity(intent)
            finish()
        }
        else{

            viewModel.fragmentNavigator?.addFragment()
        }
    }



    }


    override fun onResume() {
        super.onResume()

//        val intentObj= Intent(this@MainActivity,VideoActivity::class.java)
////        for now VideoType will only be movie
//        intentObj.putExtra("VideoType","movie")
//        startActivity(intentObj)



    }


}
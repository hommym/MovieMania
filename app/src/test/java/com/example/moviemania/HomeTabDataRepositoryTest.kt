package com.example.moviemania

import com.example.moviemania.components.customDataTypes.HomeFragmentData
import com.example.moviemania.components.libs.RetrofitConfigs
import com.example.moviemania.home.data.HomeTabDataRepository
import com.example.moviemania.home.data.HomeTabMovieRemoteData
import com.example.moviemania.home.data.HomeTabSeriesRemoteData
import junit.framework.TestCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HomeTabDataRepositoryTest {
    @Test
    fun populateTabWithDataTest(){
       val retrofitConfig= RetrofitConfigs("https://moviemaniabackend.onrender.com/api/movie/")
        val retrofitConfig1= RetrofitConfigs("https://moviemaniabackend.onrender.com/api/movie/")
       val repo = HomeTabDataRepository(retrofitConfig.config(HomeTabMovieRemoteData::class.java),retrofitConfig1.config(HomeTabSeriesRemoteData::class.java))

        runBlocking {
            launch {
                val homData= HomeFragmentData()
                repo.populateTabWithData(homData,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NjNjZTkzZjE3NGU3NDU2MjEyYmYyYTIiLCJpYXQiOjE3MjMyMzg3MjgsImV4cCI6MTcyMzMyNTEyOH0.RakRvB37DnP1BuAb51AhugWsNZs-o_ME0Y1sB-I_6ds")
                TestCase.assertNotNull("Recent movies successfully retrieved",homData.recentMovies)
//                TestCase.assertNotNull("Popular movies successfully retrieved",homData.popularMovies)
//                TestCase.assertNotNull("Trending movies successfully retrieved",homData.trendingMovies)
            }
        }

    }
}
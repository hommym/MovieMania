package com.example.moviemania.home.data


import com.example.moviemania.components.customDataTypes.HomeFragmentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class HomeTabDataRepository (private val movieRemoteData:HomeTabMovieRemoteData,private  val seriesRemoteData:HomeTabSeriesRemoteData){


    suspend fun populateTabWithData(homeFragmentData:HomeFragmentData,authHeader:String){

        withContext(Dispatchers.IO){

                homeFragmentData.apply {

                val gettingTrendingMovies= launch {
                    if(trendingMovies==null){
//                   making request to server
                    val response=   movieRemoteData.getMovie("trending", jwtToken = authHeader).execute()

                    if(response.isSuccessful){
                        trendingMovies=response.body()?.data
                    }
                    else{
                        throw  HttpException(response)
                    }

                    }
                }

                val gettingPopularMovies= launch {
                    if(popularMovies==null){
                        val response=   movieRemoteData.getMovie("popular",jwtToken = authHeader).execute()

                        if(response.isSuccessful){
                            popularMovies=response.body()?.data
                        }
                        else{
                            throw  HttpException(response)
                        }
                    }
                }

                val gettingRecentMovies=launch{

                    if(recentMovies==null){
                        val response=   movieRemoteData.getMovie("recent",jwtToken = authHeader).execute()

                        if(response.isSuccessful){
                            recentMovies=response.body()?.data
                        }
                        else{
                            throw  HttpException(response)
                        }
                    }
                }

//               coroutine scopes for getting trending ,popular and recent (not implemented)


//                  waiting for all data to be present
                    joinAll(gettingTrendingMovies,gettingPopularMovies,gettingRecentMovies)

                }




        }


    }

}
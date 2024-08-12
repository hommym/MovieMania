package com.example.moviemania.home.data

import com.example.moviemania.components.customDataTypes.MovieData
import com.example.moviemania.components.customDataTypes.MovieDetailsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeTabMovieRemoteData {

    @GET("{category}")
    fun getMovie(@Path("category")category:String,@Query("page")page:Int=1,@Header("Authorization")jwtToken:String):Call<MovieData>
    @GET("details")
    fun getDetails(@Query("movieId")movieId:Int,@Header("Authorization")jwtToken:String):Call<MovieDetailsData>

}
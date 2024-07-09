package com.example.moviemania.components.libs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfigs(private val baseUrl:String) {
    fun<T> config(requestMethods:Class<T>):T{

        val retrofit= Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(requestMethods)
    }
}
package com.example.moviemania.components

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.ResponseBody


// the purpose of this class is to help you extract error messages from response with status code between 400-500
class ApiError(private var errBody:ResponseBody?=null, private var errorMessage:String=""):Exception(errorMessage) {

    fun extractMessage():ApiError{
       this.errorMessage=(Gson().fromJson(errBody!!.string()  ,ApiErrorResponse::class.java)).err
        Log.d("LogInError", errorMessage)
        return ApiError(errorMessage=this.errorMessage)
    }


}
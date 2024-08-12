package com.example.moviemania.auth.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class JwtLocalDataSource(private val dataStore: DataStore<Preferences>) {

    companion object {
        val dataKey = stringPreferencesKey("Jwt")
    }


    suspend fun storeJwtLocally(data: String) {

        dataStore.edit {
            it[dataKey] = data
            Log.d("Saving Jwt Locally","${it[dataKey]}")
        }
    }

   suspend  fun getJwtLocally(): String? {
         return dataStore.data.first()[dataKey]

    }

}
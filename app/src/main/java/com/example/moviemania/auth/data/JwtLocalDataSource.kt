package com.example.moviemania.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

class JwtLocalDataSource(private val dataStore: DataStore<Preferences>) {

    companion object {
        val dataKey = stringPreferencesKey("Jwt")
    }


    suspend fun storeJwtLocally(data: String) {

        dataStore.edit {
            it[dataKey] = data
        }
    }

     fun getJwtLocally(): String? {

        var jwtToken: String? = null

        dataStore.data.map {
            jwtToken = it[dataKey]
        }
        return jwtToken
    }

}
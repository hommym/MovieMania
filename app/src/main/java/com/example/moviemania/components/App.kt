package com.example.moviemania.components

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class App(): Application() {
    private val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="UserJwtToken")
    var dataStoreI:DataStore<Preferences>? = null

    override fun onCreate() {
        super.onCreate()
        dataStoreI= this@App.dataStore
    }


}
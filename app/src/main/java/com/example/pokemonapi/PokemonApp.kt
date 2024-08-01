package com.example.pokemonapi

import android.app.Application
import com.example.pokemonapi.commons.util.isInternetAvailable
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Config Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
package com.example.pokemon.core

import com.example.pokemon.commons.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit():Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_PRINCIPAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.pokemonapi.di

import com.example.pokemonapi.commons.Constants.Companion.URL_PRINCIPAL
import com.example.pokemonapi.data.network.ListPokemonAPi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_PRINCIPAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ListPokemonAPi {
        return retrofit.create(ListPokemonAPi::class.java)
    }
}

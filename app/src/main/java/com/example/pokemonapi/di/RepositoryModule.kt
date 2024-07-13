package com.example.pokemonapi.di

import android.app.Application
import androidx.room.Room
import com.example.pokemonapi.data.local.PokemonDataBase
import com.example.pokemonapi.data.local.dao.PokemonListDao
import com.example.pokemonapi.data.remote.PokemonApi
import com.example.pokemonapi.data.repository.PokemonRepositoryImpl
import com.example.pokemonapi.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemon(
        recipesApi: PokemonApi,
        pokemonListDao: PokemonListDao
    ): PokemonRepository = PokemonRepositoryImpl(recipesApi,pokemonListDao)

    @Singleton
    @Provides
    fun provideDataBase(application: Application): PokemonDataBase {
        return Room.databaseBuilder(application, PokemonDataBase::class.java, "PokemonDB").build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(dataBase: PokemonDataBase): PokemonListDao {
        return dataBase.pokemonListDao()
    }
}
package com.example.pokemonapi.di

import com.example.pokemonapi.data.network.PokemonApi
import com.example.pokemonapi.data.repository.PokemonRepositoryImpl
import com.example.pokemonapi.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePokemon(
        recipesApi: PokemonApi,
    ): PokemonRepository = PokemonRepositoryImpl(recipesApi)
}
package com.example.pokemonapi.data.network

import com.example.pokemonapi.data.model.response.ListPokemonResponse
import com.example.pokemonapi.data.model.response.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListPokemonService @Inject constructor(private val api: ListPokemonAPi) {

    suspend fun getPokemonService(): ListPokemonResponse? {
        return withContext(Dispatchers.IO)
        {
            val response = api.getListPokemonApi()
            response.body()
        }
    }

    suspend fun getInfoPokemonService(id: Int): Pokemon? {
        return withContext(Dispatchers.IO)
        {
            val response = api.listInfoPokemon(id)
            response.body()
        }
    }

}
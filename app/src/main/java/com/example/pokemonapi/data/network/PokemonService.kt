package com.example.pokemonapi.data.network

import com.example.pokemonapi.data.model.response.PokemonResponse
import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val api: PokemonApi) {

    suspend fun getPokemonService(): PokemonResponse? {
        return withContext(Dispatchers.IO)
        {
            val response = api.getListPokemonApi()
            response.body()
        }
    }

    suspend fun getInfoPokemonService(id: Int): PokemonInfoResponse? {
        return withContext(Dispatchers.IO)
        {
            val response = api.listInfoPokemon(id)
            response.body()
        }
    }

}
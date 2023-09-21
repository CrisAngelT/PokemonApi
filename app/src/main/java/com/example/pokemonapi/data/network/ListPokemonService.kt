package com.example.pokemonapi.data.network

import com.example.pokemon.core.RetrofitHelper
import com.example.pokemonapi.data.model.response.ListPokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListPokemonService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun  getPokemonService(): ListPokemonResponse?
    {
        return withContext(Dispatchers.IO)
        {
            val response = retrofit.create(ListPokemonAPi::class.java).getListPokemonApi()
            response.body()
        }
    }
}
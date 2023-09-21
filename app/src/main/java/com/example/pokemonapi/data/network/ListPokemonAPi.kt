package com.example.pokemonapi.data.network

import com.example.pokemonapi.data.model.response.ListPokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListPokemonAPi {

    @GET("pokemon")
   suspend fun getListPokemonApi():Response<ListPokemonResponse>
}
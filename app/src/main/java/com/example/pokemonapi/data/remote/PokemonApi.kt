package com.example.pokemonapi.data.remote

import com.example.pokemonapi.data.model.response.PokemonResponse
import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import com.example.pokemonapi.data.model.response.evolution.PokemonEvolutionResponse
import com.example.pokemonapi.data.model.response.move.MoveResponse
import com.example.pokemonapi.data.model.response.species.SpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/?offset=0&limit=100")
    suspend fun getListPokemonApi(): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun listInfoPokemon(@Path("id") id: Int): Response<PokemonInfoResponse>

    @GET("evolution-chain/{id}")
    suspend fun evolutionChain(@Path("id") id: Int): Response<PokemonEvolutionResponse>

    @GET("pokemon-species/{name}")
    suspend fun species(@Path("name") name: String): Response<SpeciesResponse>

    @GET("move/{id}")
    suspend fun movePokemon(@Path("id") id: Int): Response<MoveResponse>
}
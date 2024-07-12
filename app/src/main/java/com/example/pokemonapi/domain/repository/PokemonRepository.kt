package com.example.pokemonapi.domain.repository
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.PokemonBean
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemon(): Flow<PokemonBean>

    suspend fun getInfoPokemon(idPokemon: Int): Flow<DetailPokemonBean>

}
package com.example.pokemonapi.domain.repository
import com.example.pokemonapi.data.local.entity.PokemonListEntity
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.PokemonBean
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemon(): Flow<List<ResultPokemonBean>>

    suspend fun getInfoPokemon(idPokemon: Int): Flow<DetailPokemonBean>

}
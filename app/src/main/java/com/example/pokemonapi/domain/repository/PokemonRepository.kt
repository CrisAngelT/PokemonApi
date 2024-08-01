package com.example.pokemonapi.domain.repository
import com.example.pokemonapi.data.model.response.move.MoveResponse
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.domain.bean.evolution.EvolutionPokemonBean
import com.example.pokemonapi.domain.bean.move.MoveBean
import com.example.pokemonapi.domain.bean.species.SpeciesBean
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemon(): Flow<List<ResultPokemonBean>>
    suspend fun getInfoPokemon(idPokemon: Int): Flow<DetailPokemonBean>
    suspend fun evolutionPokemon(idPokemon: Int): Flow<EvolutionPokemonBean>
    suspend fun speciesPokemon(name: String): Flow<SpeciesBean>
    suspend fun movePokemon(idPokemon: Int): Flow<MoveBean>
}
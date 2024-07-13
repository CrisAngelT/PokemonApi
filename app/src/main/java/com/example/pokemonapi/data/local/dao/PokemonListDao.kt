package com.example.pokemonapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapi.data.local.entity.PokemonListEntity

@Dao
interface PokemonListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListPokemon(pokemonListEntity: List<PokemonListEntity>): List<Long>

    @Query("SELECT * FROM PokemonListEntity")
    suspend fun getPokemon(): List<PokemonListEntity>
}
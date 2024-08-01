package com.example.pokemonapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapi.data.local.entity.DetailPokemonEntity
import com.example.pokemonapi.data.local.entity.PokemonListEntity

@Dao
interface DetailPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailPokemon(detailPokemonEntity: DetailPokemonEntity):Long

    @Query("SELECT * FROM DetailPokemonEntity WHERE idPokemon = :idPokemon")
    suspend fun getDetailPokemon(idPokemon: Int): List<PokemonListEntity>
}
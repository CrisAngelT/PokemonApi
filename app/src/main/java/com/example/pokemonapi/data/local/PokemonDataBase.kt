package com.example.pokemonapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonapi.data.local.dao.PokemonListDao
import com.example.pokemonapi.data.local.entity.PokemonListEntity


@Database(entities = [PokemonListEntity::class], version = 1)
abstract class PokemonDataBase: RoomDatabase() {
    abstract fun pokemonListDao(): PokemonListDao

}
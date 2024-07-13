package com.example.pokemonapi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PokemonListEntity(
    @PrimaryKey
    val idPokemon: Int,
    val name: String,
    val url: String
)

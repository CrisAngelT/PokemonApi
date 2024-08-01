package com.example.pokemonapi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailPokemonEntity (
    @PrimaryKey
    val idPokemon: Int,
    val totalCp:Int,
    val namePokemon:String,
    val height:Int,
    val weight:Int,
    val listTypePokemon:String,
    val soundPokemon:String,
    val listTypes:String,
    val listStats:String

)
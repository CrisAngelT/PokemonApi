package com.example.pokemonapi.domain

import com.example.pokemon.data.ListPokemonRepository
import com.example.pokemonapi.data.model.bean.ListPokemonBean

class GetPokemonUseCase {
    private val repository = ListPokemonRepository()

    suspend operator fun invoke(): ListPokemonBean? = repository.getListPokemonRepository()

}
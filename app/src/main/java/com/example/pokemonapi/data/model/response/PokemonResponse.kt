package com.example.pokemonapi.data.model.response

import com.example.pokemonapi.domain.bean.PokemonBean
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val results: List<ResultPokemonResponse> = listOf()
) : java.io.Serializable {
    fun toBean(): PokemonBean {
        val bean = this

        return PokemonBean(
            resultsListPokemonBean = bean.results.map {
                it.toBean()
            })

    }
}
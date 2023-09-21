package com.example.pokemonapi.data.model.response

import com.example.pokemonapi.domain.bean.ListPokemonBean
import com.google.gson.annotations.SerializedName

data class ListPokemonResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: Any?,
    @SerializedName("results") val results: List<ResultPokemonResponse>?
) : java.io.Serializable {
    fun toBean(): ListPokemonBean {
        val bean = this

        return ListPokemonBean(
            count = bean.count,
            next = bean.next,
            previous = bean.previous,
            results = bean.results?.map {
                it.toBean()
            })

    }
}
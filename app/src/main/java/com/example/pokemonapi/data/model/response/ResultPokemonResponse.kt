package com.example.pokemonapi.data.model.response

import com.example.pokemonapi.data.model.bean.ResultPokemonBean
import com.google.gson.annotations.SerializedName

data class ResultPokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : java.io.Serializable {


    fun toBean(): ResultPokemonBean {
        val bean = this
        return ResultPokemonBean(name = bean.name, url = bean.url)

    }
}
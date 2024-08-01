package com.example.pokemonapi.data.model.response.move

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NormalResponse(
    @Expose @SerializedName("use_after")  val useAfter: List<UseBeforeResponse>?=null,
    @Expose @SerializedName("use_before") val useBefore: List<UseBeforeResponse>?=null
)
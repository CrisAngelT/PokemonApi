package com.example.pokemonapi.data.model.response.move

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @Expose @SerializedName("ailment_chance") val ailmentChance: Int,
    @Expose @SerializedName("category") val category: CategoryResponse,
    @Expose @SerializedName("crit_rate") val critRate: Int,
    @Expose @SerializedName("drain") val drain: Int,
    @Expose @SerializedName("flinch_chance") val flinchChance: Int,
    @Expose @SerializedName("healing") val healing: Int,
//    @Expose @SerializedName("max_hits") val max_hits: Any,
//    @Expose @SerializedName("max_turns") val max_turns: Any,
//    @Expose @SerializedName("min_hits") val min_hits: Any,
//    @Expose @SerializedName("min_turns") val min_turns: Any,
//    @Expose @SerializedName("stat_chance") val stat_chance: Int
)
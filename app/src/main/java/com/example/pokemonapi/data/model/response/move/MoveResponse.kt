package com.example.pokemonapi.data.model.response.move

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoveResponse(
    @Expose @SerializedName("accuracy")  val accuracy: Int,
    @Expose @SerializedName("contest_combos")   val contestCombos: ContestCombos?=null,
    @Expose @SerializedName("effect_entries")  val effectEntries: List<EffectEntryResponse>,
    @Expose @SerializedName("flavor_text_entries")  val flavorTextEntries: List<FlavorTextEntry>,
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("meta") val meta: MetaResponse,
    @Expose @SerializedName("name")  val name: String,
    @Expose @SerializedName("power")  val power: Int,
    @Expose @SerializedName("pp")  val pp: Int,
    @Expose @SerializedName("priority") val priority: Int
)
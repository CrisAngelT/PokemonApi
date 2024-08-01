package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @Expose @SerializedName("base_happiness") val baseHappiness: Int,
    @Expose @SerializedName("capture_rate") val captureRate: Int,
    @Expose @SerializedName("color") val color: ColorResponse,
    @Expose @SerializedName("egg_groups") val eggGroups: List<EggGroupResponse>,
    @Expose @SerializedName("evolution_chain") val evolutionChain: EvolutionChainResponse,
    @Expose @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntryResponse>,
    @Expose @SerializedName("gender_rate") val genderRate: Int,
    @Expose @SerializedName("growth_rate") val growthRate: GrowthRateResponse,
    @Expose @SerializedName("habitat") val habitat: HabitatResponse,
    @Expose @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean,
    @Expose @SerializedName("hatch_counter") val hatchCounter: Int,
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("is_baby") val isBaby: Boolean,
    @Expose @SerializedName("is_legendary") val isLegendary: Boolean,
    @Expose @SerializedName("is_mythical") val isMythical: Boolean,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("order") val order: Int,
    @Expose @SerializedName("pal_park_encounters") val palParkEncounters: List<PalParkEncounterResponse>,
    @Expose @SerializedName("pokedex_numbers") val pokedexNumbers: List<PokedexNumberResponse>,
    @Expose @SerializedName("shape") val shape: ShapeResponse,
    @Expose @SerializedName("varieties") val varieties: List<VarietyResponse>
)
package com.example.pokemonapi.domain.mapper

import com.example.pokemonapi.data.model.response.move.ContestCombos
import com.example.pokemonapi.data.model.response.move.MoveResponse
import com.example.pokemonapi.domain.bean.move.CombosBean
import com.example.pokemonapi.domain.bean.move.MoveBean

fun MoveResponse.toDomain():MoveBean{
    val spanishFlavorText = flavorTextEntries
        .filter { it.language.name == "es" }
        .map { it.flavorText }
        .firstOrNull()
        .orEmpty()
    return MoveBean(
        power = power,
        accuracy = accuracy,
        pp = pp,
        combosBean = this.contestCombos?.toDomain(),
        spanishFlavorText = spanishFlavorText
    )
}

fun ContestCombos.toDomain(): CombosBean {
 return CombosBean(
     combosUseAfter = this.normal?.useAfter?.map { it.name },
     combosUseBefore = this.normal?.useBefore?.map { it.name }

 )
}
package com.example.pokemonapi.commons.util

fun dividerHeightAndWithPokemon(integer:Int = 10):String{
    val divider = integer/10.0
    return divider.toString()
}

fun dividerWithStats(integer: Int): Float {
    return integer / 100f
}

fun replaceWithChar(word:String):String{
    return word.replaceFirstChar { it.uppercase() }
}


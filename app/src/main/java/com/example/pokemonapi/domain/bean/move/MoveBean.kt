package com.example.pokemonapi.domain.bean.move

data class MoveBean(
    val power:Int = 1,
    val accuracy:Int = 200,
    val pp:Int = 200,
    val combosBean: CombosBean?=null,
    val spanishFlavorText:String = "Golpea con las patas o la cola.",

)

data class CombosBean(
    val combosUseAfter:List<String>? =null,
    val combosUseBefore:List<String>?=null,
)

package com.example.pokemonapi.ui.map

import android.os.Parcel
import android.os.Parcelable
import com.example.pokemonapi.commons.firebase.PokemonLocation

data class MapState(
    val listPokemon: List<PokemonLocation?> = listOf(),
    val isLoading: Boolean = false


) : Parcelable {
    constructor(parcel: Parcel) : this(
        mutableListOf<PokemonLocation?>().apply {
            parcel.readList(this, PokemonLocation::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeList(listPokemon)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<MapState> {
        override fun createFromParcel(parcel: Parcel): MapState {
            return MapState(parcel)
        }

        override fun newArray(size: Int): Array<MapState?> {
            return arrayOfNulls(size)
        }
    }

}

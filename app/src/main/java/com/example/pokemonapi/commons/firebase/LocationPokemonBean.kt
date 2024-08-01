package com.example.pokemonapi.commons.firebase

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.GeoPoint


data class PokemonLocation(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val location:GeoPoint?=null
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(GeoPoint::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(image)
        location?.let {
            parcel.writeDouble(it.latitude)
            parcel.writeDouble(it.longitude)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonLocation> {
        override fun createFromParcel(parcel: Parcel): PokemonLocation {
            return PokemonLocation(parcel)
        }

        override fun newArray(size: Int): Array<PokemonLocation?> {
            return arrayOfNulls(size)
        }
    }

}


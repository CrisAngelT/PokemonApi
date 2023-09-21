package com.example.pokemonapi.commons

sealed class Resource<T>(
    val data: T? = null,
    val errorMessageOrCode: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorMessageOrCode:String) : Resource<T>(null, errorMessageOrCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorMessageOrCode]"
            is Loading<T> -> "Loading"
        }
    }
}
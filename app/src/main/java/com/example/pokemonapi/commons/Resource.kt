package com.example.pokemonapi.commons

sealed class Resource<T>(
    val data: T? = null,
    val errorMessageOrCode: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()

    class DataError<T>(message: String): Resource<T>(errorMessageOrCode = message)


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorMessageOrCode]"
            is Loading<T> -> "Loading"
        }
    }
}
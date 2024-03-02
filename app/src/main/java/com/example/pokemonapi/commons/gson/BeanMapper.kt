package com.example.pokemonapi.commons.gson

import android.content.Intent
import android.os.Build
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.io.Serializable
import java.lang.reflect.Type

object BeanMapper {

    fun <T> fromJson(jsonAsString: String?, BeanClass: Class<T>?): T {
        return Gson().fromJson(jsonAsString, BeanClass)
    }
    fun <T> fromJson(jsonAsString: String?, BeanClass: Type?): T {
        return Gson().fromJson(jsonAsString, BeanClass)
    }
    fun toJson(bean: Any?): String {
        return toJson(bean, true)
    }

    private fun toJson(bean: Any?, prettyPrint: Boolean): String {
        val gson: Gson = if (prettyPrint) {
            GsonBuilder().setPrettyPrinting().serializeNulls().create()
        } else {
            GsonBuilder().registerTypeHierarchyAdapter(
                ByteArray::class.java,
                ByteArrayToBase64TypeAdapter()
            ).serializeNulls().create()
        }
        return gson.toJson(bean)
    }
    class ByteArrayToBase64TypeAdapter : JsonSerializer<ByteArray?>,
        JsonDeserializer<ByteArray> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): ByteArray {
            return Base64.decode(json.asString, 2)
        }

        override fun serialize(
            src: ByteArray?,
            typeOfSrc: Type,
            context: JsonSerializationContext
        ): JsonElement {
            return JsonPrimitive(Base64.encodeToString(src, 2))
        }
    }
}

fun <T : Serializable?> getSerializable(intent: Intent?, name: String, clazz: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent?.getSerializableExtra(name, clazz)!!
    else
        intent?.getSerializableExtra(name) as T
}
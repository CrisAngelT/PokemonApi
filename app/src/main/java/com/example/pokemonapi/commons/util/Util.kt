package com.example.pokemonapi.commons.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

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
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}
fun extractIdFromUrl(url: String): Int {
    val regex = """/(\d+)/""".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groups?.get(1)?.value?.toInt() ?: 0
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}


suspend fun bitmapDescriptorFromUrl(context: Context, url: String, width: Int, height: Int): BitmapDescriptor? {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false) // Asegúrate de que la imagen no use bitmaps de hardware
        .build()

    return try {
        val result = (loader.execute(request) as SuccessResult).drawable
        val originalBitmap = Bitmap.createBitmap(
            result.intrinsicWidth,
            result.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(originalBitmap)
        result.setBounds(0, 0, canvas.width, canvas.height)
        result.draw(canvas)

        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    } catch (e: Exception) {
        null
    }
}

suspend fun bitmapDescriptorFromSvgUrl(context: Context, url: String, width: Int, height: Int): BitmapDescriptor? {
    val loader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false)
        .build()

    return try {
        val result = (loader.execute(request) as SuccessResult).drawable
        val originalBitmap = Bitmap.createBitmap(
            result.intrinsicWidth,
            result.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(originalBitmap)
        result.setBounds(0, 0, canvas.width, canvas.height)
        result.draw(canvas)

        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


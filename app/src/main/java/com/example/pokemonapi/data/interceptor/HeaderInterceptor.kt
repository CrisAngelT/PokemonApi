package com.example.pokemonapi.data.interceptor

import android.os.Build
import com.example.pokemonapi.commons.gson.BeanMapper
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("OS", "Android-${Build.VERSION.SDK_INT}")
            .addHeader("Version", "")
            .build()

        return chain.proceed(request)
    }
}
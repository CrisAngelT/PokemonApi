package com.example.pokemonapi.di

import com.example.pokemonapi.commons.Constants.Companion.URL_PRINCIPAL
import com.example.pokemonapi.data.interceptor.HeaderInterceptor
import com.example.pokemonapi.data.network.ListPokemonAPi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        interceptor: HeaderInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(interceptor)
        .build()
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_PRINCIPAL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ListPokemonAPi {
        return retrofit.create(ListPokemonAPi::class.java)
    }
}
private const val TIME_OUT = 1L
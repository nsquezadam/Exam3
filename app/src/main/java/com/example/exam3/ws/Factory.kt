package com.example.exam3.ws

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Factory {

    // singleton

    fun getBaseUrl(): String = "https://mindicador.cl"
    // funcion necesaria para mapeo
    fun getService(serviceClass: Class<*>): Any {
        val adapter = KotlinJsonAdapterFactory()
        val moshi = Moshi.Builder().add(adapter).build()
        val converter = MoshiConverterFactory.create(moshi)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(getBaseUrl())
            .build()
        return retrofit.create(serviceClass)
    }

    // tiene mas de  una interface
    fun getCostService():ReservaService{
        return getService(ReservaService::class.java) as ReservaService
    }

}
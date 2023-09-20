package com.example.exam3.ws

import retrofit2.http.GET
import retrofit2.http.Query

interface ReservaService {
    //url  https://mindicador.cl/api
    @GET("/api/dolar/serie")
    suspend fun buscar(@Query("fecha") fechaBusqueda: String):ReservaCost

}
package com.example.exam3.ws

import com.squareup.moshi.Json
import java.util.Date

data class  BusquedaCosto(
    val codigo:String,
    val valor: Int,
    val fecha: String

)
data class ReservaCost(
    // cod
    //valor dolar peso chileno
    @Json(name= "valor")
    val valor:Int,
    val fecha:String
)

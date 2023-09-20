package com.example.exam3.main

import com.example.exam3.data.Reserva

data class ReservaState(




    val reservas : List<Reserva> = emptyList<Reserva>(),
    val isLoading: Boolean = false,
    val id:Int? = null,
    val lugar:String = "",
    val urlImage :String= "",
    val latitud :String ="",
    val longitud:String ="",
    val orden : Int =0,
    val costoAlojamiento :Int=0,
    val costoTraslado :Int=0,
    val comentarios:String=""

)

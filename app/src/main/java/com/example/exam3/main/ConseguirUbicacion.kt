package com.example.exam3.main

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority


// excepcion personalizada
class FaltaPermisosException(mensaje:String): Exception(mensaje)


fun conseguirUbicacion(contexto: Context, onSuccess:(ubicacion: Location)-> Unit){
    try {
        val servicio = LocationServices.getFusedLocationProviderClient(contexto)
        val tarea =servicio.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        )
        tarea.addOnSuccessListener {
            onSuccess(it)
        }
    }catch(se: SecurityException){
        throw FaltaPermisosException("Falta Permisos")
    }


}

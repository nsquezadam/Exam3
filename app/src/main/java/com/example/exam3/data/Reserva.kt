package com.example.exam3.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Reserva(
    @PrimaryKey(autoGenerate = true) val id :Int? = null,
    val lugar:String,
    val urlImage :String?=null,
    val latitud :String,
    val longitud:String,
    val orden : Int ,
    val costoAlojamiento :Int,
    val costoTraslado :Int,
    val comentarios:String
){
 val imagenUrl :String
     get(){
         if (urlImage != null){
             return urlImage
         }else{
             return "https://upload.wikimedia.org/wikipedia/commons/d/da/Imagen_no_disponible.svg"
         }
     }


}

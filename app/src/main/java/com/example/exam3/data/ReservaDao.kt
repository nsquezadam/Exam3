package com.example.exam3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ReservaDao {

   @Query("SELECT * FROM reserva order by id")
    fun getAll():List<Reserva>
   @Insert
    fun insert(reserva:Reserva):Long
   @Update
    fun update(reserva:Reserva)
   @Delete
    fun delete(reserva:Reserva)
}
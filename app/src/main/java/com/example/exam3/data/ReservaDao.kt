package com.example.exam3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ReservaDao {

   @Query("SELECT * FROM reserva order by id")
   fun getAll():Flow<List<Reserva>>
   @Query("SELECT * FROM reserva WHERE id= :id")
  fun getReservaById(id:Int): Flow<List<Reserva>>
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reserva:Reserva):Long
   @Update
   suspend fun update(reserva:Reserva)
   @Delete
   suspend fun delete(reserva:Reserva)
}
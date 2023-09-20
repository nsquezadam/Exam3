package com.example.exam3.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam3.data.Reserva
import com.example.exam3.data.ReservaDao
import com.example.exam3.main.ReservaState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReservaViewModel(
    private val reservaDao:ReservaDao
): ViewModel() {
    val latitud = mutableStateOf(0.0)
    val longitud = mutableStateOf(0.0)

    var permisoUbicacionOk:() -> Unit = {}
    var state by  mutableStateOf(ReservaState())
        private set




    //estados
    fun onLugarChange(lugar:String){ state = state.copy(lugar = lugar)}
    fun onImagenUrlChange(url:String){ state = state.copy(urlImage = url)}
    fun onLatituChange(lat:String){ state = state.copy(latitud = lat)}
    fun onLongitudChange(long:String){ state = state.copy(longitud = long)}
    fun onOrdenlChange(orden:Int){ state = state.copy(orden = orden)}
    fun onCostAlojamientoChange(costA:Int){ state = state.copy(costoAlojamiento = costA)}
    fun onCostTrasladosChange(costT:Int){ state = state.copy(costoTraslado = costT)}
    fun onComentariosChange(comentario:String){ state = state.copy(comentarios = comentario)}

    // guardar


    init{
        viewModelScope.launch {
            reservaDao.getAll().collectLatest {
                state = state.copy(
                    reservas = it
                )
            }

        }


    }


    fun agregarReserva(reserva: Reserva) = viewModelScope.launch {
        reservaDao.insert(reserva= reserva)
    }

    fun actualizarReserva(reserva: Reserva) = viewModelScope.launch {
        reservaDao.update(reserva= reserva)
    }
    fun eliminarReserva(reserva: Reserva) = viewModelScope.launch {
        reservaDao.delete(reserva= reserva)
    }
}
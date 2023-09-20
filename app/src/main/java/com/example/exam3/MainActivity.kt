package com.example.exam3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import com.example.exam3.data.AppDatabase
import com.example.exam3.navegacion.NavManager
import com.example.exam3.viewModel.ReservaViewModel


class MainActivity : ComponentActivity() {
    val appVM: ReservaViewModel by viewModels()

    val lanzadorPermisos = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){
        if (
            (it[android.Manifest.permission.ACCESS_FINE_LOCATION]?: false) or
            (it[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false)) {
            appVM.permisoUbicacionOk()

        }else{
            Log.v("Lanzamiento de permiso callback","Se denegaron los permisos")
        }



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val dao = AppDatabase.getInstance(context).reservaDao()
            val viewModel = ReservaViewModel(dao)
            
            NavManager(viewModel = viewModel)
        }
    }
}





    
    
    


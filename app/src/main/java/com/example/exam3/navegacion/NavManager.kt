package com.example.exam3.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exam3.viewModel.ReservaViewModel
import com.example.exam3.views.AgregarView
import com.example.exam3.views.InicioView
import com.example.exam3.views.ModificarView

@Composable
fun NavManager(viewModel: ReservaViewModel){
    val  navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "inicio" ){
        composable("inicio"){
            InicioView(navController,viewModel)}
        composable("agregar"){
            AgregarView(navController,viewModel)}
        composable("editar/{id}/{lugar}/{imagenUrl}/{latitud}/{longitud}/{orden}/{costoAlojamiento}/{costoTraslados}/{comentarios}",
                    arguments = listOf(
                        navArgument("id"){type= NavType.IntType},
                        navArgument("lugar"){type= NavType.StringType},
                        navArgument("imagenUrl"){type= NavType.StringType},
                        navArgument("latitud"){type= NavType.StringType},
                        navArgument("longitud"){type= NavType.StringType},
                        navArgument("orden"){type= NavType.IntType},
                        navArgument("costoAlojamiento"){type= NavType.IntType},
                        navArgument("costoTraslados"){type= NavType.IntType},
                        navArgument("comentarios"){type= NavType.StringType},
                    )){
            ModificarView(navController,
                          viewModel,
                          it.arguments!!.getInt("id"),
                          it.arguments?.getString("lugar"),
                          it.arguments?.getString("imagenUrl"),
                          it.arguments?.getString("latitud"),
                          it.arguments?.getString("longitud"),
                          it.arguments?.getInt("orden"),
                          it.arguments?.getInt("costoAlojamiento"),
                          it.arguments?.getInt("costoTraslado"),
                          it.arguments?.getString("comentarios"),)}

    }
    

}
package com.example.exam3.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.exam3.viewModel.ReservaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController:NavController, viewModel: ReservaViewModel){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                      Text(text = "Inicio" , color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription ="agregar" )

            }
            Text(text = "Agregar", color = Color.White)
        }
        
    ) {
        ContentInicioView(it, navController, viewModel)
    }



}

@Composable
fun ContentInicioView(it: PaddingValues,navController: NavController, viewModel: ReservaViewModel ){
    val state = viewModel.state
    // aplicar traer el costo del dolar


    Column(modifier = Modifier.padding(it)) {
        LazyColumn{
           items(state.reservas){

               Box(modifier = Modifier
                   .padding(8.dp)
                   .fillMaxWidth()){
                   Row (modifier= Modifier
                       .fillMaxWidth()
                       .padding(10.dp)){
                       Box (
                           modifier = Modifier.widthIn(min =100.dp, max=100.dp),
                           contentAlignment = Alignment.Center
                       ){

                           AsyncImage(model = it.urlImage, contentDescription = "Imagen")
                       }
                       Column (modifier = Modifier.padding(10.dp)){
                           Row {
                               Text(text = "Nombre Lugar  ",modifier= Modifier.weight(2f))
                               Text(text =it.lugar)
                           }
                           Row {

                               Text(text = "Costo x Noche  ",modifier= Modifier.weight(2f))
                               Text(text = (it.costoAlojamiento ).toString())
                           }
                           Row {
                               Text(text = "Traslado")
                               Text(text = (it.costoTraslado).toString())
                           }
                           Row {
                               // iconos eliminar , modficar
                               Icon( Icons.Filled.LocationOn, contentDescription ="localizacion",modifier = Modifier.clickable {  } )
                               IconButton(onClick = { /*TODO*/ }) { Icon(
                                   imageVector = Icons.Default.LocationOn,
                                   contentDescription = "Ubicacion"
                               )}
                               IconButton(onClick = {
                                   navController.navigate("editar/${it.id}/${it.lugar}/${it.urlImage}/${it.latitud}/${it.longitud}/${it.orden}/${it.costoAlojamiento}/${it.costoTraslado}/${it.comentarios}")
                               }) {
                                   Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                               }
                               IconButton(onClick = {viewModel.eliminarReserva(it) } ) {
                                   Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                               }

                           }


                       }
                   }

               }
           }
        }
    }


}
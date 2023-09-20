package com.example.exam3.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.exam3.R
import com.example.exam3.data.Reserva
import com.example.exam3.viewModel.ReservaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarView(navController:NavController,
                  viewModel: ReservaViewModel,
                  id:Int,
                  lugar:String?,
                  imagenUrl:String?,
                  latitud:String?,
                  longitud:String?,
                  orden:Int?,
                  costoAlojamiento:Int?,
                  costoTraslado:Int?,
                  comentarios:String?){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Modificar" , color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription ="Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        },



        ) {
        ContentModificarView(
            it,
            navController,
            viewModel,
            id,
            lugar,
            imagenUrl,
            latitud,
            longitud,
            orden,
            costoAlojamiento,
            costoTraslado,
            comentarios )
    }


}

@Composable
fun ContentModificarView(it:PaddingValues,
                         navController: NavController,
                         viewModel: ReservaViewModel,
                         id:Int,
                         lugar:String?,
                         imagenUrl:String?,
                         latitud:String?,
                         longitud:String?,
                         orden:Int?,
                         costoAlojamiento:Int?,
                         costoTraslado:Int?,
                         comentarios:String?
                         ){
    var lugar by remember{ mutableStateOf("") }
    var url by  remember{ mutableStateOf("") }
    var lat by remember{ mutableStateOf("") }
    var long by remember{ mutableStateOf("") }
    var orden by remember{ mutableStateOf(0) }
    var costAlo by remember{ mutableStateOf(0) }
    var costTra by remember{ mutableStateOf(0) }
    var comentario by remember{ mutableStateOf("") }
    // se aplica recursos de texto para cambiar texto segun idioma
    // y se baja  variable  para que puedan consumirse en el formulario
    val context = LocalContext.current
    val resources = context.resources
    Column(
        modifier= Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = lugar ?:"" ,
            onValueChange ={lugar = it},
            label={ Text(text = resources.getString(R.string.lugar) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = url?:"" ,
            onValueChange ={url = it},
            label={ Text(text = resources.getString(R.string.imagenUrl) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = lat?:"" ,
            onValueChange ={lat = it},
            label={ Text(text = resources.getString(R.string.latlog) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = long ?:"",
            onValueChange ={long = it},
            label={ Text(text = resources.getString(R.string.latlog) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = orden.toString()?:"" ,
            onValueChange ={orden = it.toInt()},
            label={ Text(text = resources.getString(R.string.orden) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = costAlo.toString() ?:"",
            onValueChange ={costAlo = it.toInt()},
            label={ Text(text = resources.getString(R.string.costoAloj) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = costTra.toString()?:"" ,
            onValueChange ={costTra = it.toInt()},
            label={ Text(text = resources.getString(R.string.costoTraslados) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        OutlinedTextField(
            value = comentario?:"",
            onValueChange ={comentario = it},
            label={ Text(text = resources.getString(R.string.comentarios) )},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp))
        Button(
            onClick = {
                val reser = Reserva(
                    id=id,
                    lugar = lugar!!,
                    urlImage = url!!,
                    latitud = lat!!,
                    longitud = long!!,
                    orden = orden!!,
                    costoAlojamiento = costAlo!!,
                    costoTraslado = costTra!!,
                    comentarios = comentario!!
                )
                viewModel.actualizarReserva(reser)
                navController.popBackStack()
            }) { Text(text = resources.getString(R.string.guardar) )

        }

    }




}
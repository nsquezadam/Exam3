package com.example.exam3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.exam3.data.Acciones
import com.example.exam3.data.AppDatabase
import com.example.exam3.data.Reserva
import com.example.exam3.ws.Factory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FormView:ViewModel(){



}





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // se aplica recursos de texto para cambiar texto segun idioma
        // y se baja  variable  para que puedan consumirse en el formulario
        val lugar   = resources.getString(R.string.lugar)
        val imagen  = resources.getString(R.string.imagenUrl)
        val latlong = resources.getString(R.string.latlog)
        val ord     = resources.getString(R.string.orden)
        val costA   = resources.getString(R.string.costoAloj)
        val costT   = resources.getString(R.string.costoTraslados)
        val comen   = resources.getString(R.string.comentarios)
        val btnSave = resources.getString(R.string.guardar)
        val btnBack = resources.getString(R.string.regresar)

        setContent {

           MainApp()
        }
    }
}

@Composable
fun MainApp()
{
        val contexto = LocalContext.current
        val (reserva, SetReserva) = remember {mutableStateOf(  emptyList<Reserva>())}
        val (seleccion, setSeleccion) = remember { mutableStateOf<Reserva?>(null) }
        val (accion, setAccion) = remember { mutableStateOf(Acciones.LISTAR) }
    LaunchedEffect(reserva ){
           withContext(Dispatchers.IO){
               val  db = AppDatabase.getInstance(contexto)
               SetReserva(db.reservaDao().getAll())
           }
    }
    val onSave= {
           setAccion(Acciones.LISTAR)
           SetReserva(emptyList())
    }
    when (accion){
        Acciones.CREAR -> FormApp(r = null, onSave)
        Acciones.EDITAR -> FormApp(seleccion, onSave)
        else -> ListRevUI()

    }

}








@Composable
fun FormApp(
    r: Reserva?,
    onSave:()->Unit={},) {
    val context = LocalContext.current
    val alcanceCorrutina = rememberCoroutineScope()
    val (lugar, SetLugar) = remember { mutableStateOf(r?.lugar ?: "") }
    val (latitud, SetLatitud) = remember { mutableStateOf(r?.latitud ?: "") }
    val (imagenUrl, SetImagenUrl) = remember { mutableStateOf(r?.imagenUrl ?: "") }
    val (longitud, SetLongitud) = remember { mutableStateOf(r?.longitud ?: "") }
    val (orden, SetOrden) = remember { mutableStateOf(r?.orden ?: 0) }
    val (costoAlojamiento, SetCostAlojamiento) = remember {mutableStateOf(r?.costoAlojamiento ?: 0)}
    val (costoTraslados, SetCostoTraslados) = remember { mutableStateOf(r?.costoTraslado ?: 0) }
    val (comentarios, SetComentarios) = remember { mutableStateOf(r?.comentarios ?: " ") }
    val snackbarHostState = remember { SnackbarHostState() }
    // se aplica recursos de texto para cambiar texto segun idioma
    // y se baja  variable  para que puedan consumirse en el formulario
    val resources = context.resources
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            Text(text = resources.getString(R.string.lugar))
            TextField(
                value = lugar,
                onValueChange = { SetLugar(it) },
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.imagenUrl))
            TextField(
                value = imagenUrl,
                onValueChange = { SetImagenUrl(it) },
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.latlog))
            Row {
                TextField(
                    value = latitud,
                    onValueChange = { SetLatitud(it) },
                )

                TextField(
                    value = longitud,
                    onValueChange = { SetLongitud(it) },
                )
            }




            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.orden))
            TextField(
                value = orden.toString(),
                onValueChange = { SetOrden(it.toInt()) },
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.costoAloj))
            TextField(
                value = costoAlojamiento.toString(),
                onValueChange = { SetCostAlojamiento(it.toInt()) },
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.costoTraslados))
            TextField(
                value = costoTraslados.toString(),
                onValueChange = { SetCostoTraslados(it.toInt()) },
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = resources.getString(R.string.comentarios))
            TextField(
                value = comentarios,
                onValueChange = { SetComentarios(it) },
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = resources.getString(R.string.guardar))

                }
            }
        }
    }
}

@Composable
fun ListRevUI(){
    val context = LocalContext.current
    val (reserva, setReservas) = remember { mutableStateOf(emptyList<Reserva>()) }
   LaunchedEffect(Unit){
       withContext(Dispatchers.IO){
          val dao =  AppDatabase.getInstance(context).reservaDao()
          setReservas(dao.getAll())
       }
   }

   LazyColumn(){
          items(reserva) {rev->
              itemUI(rev);
          }


   }


}
@Composable
fun itemUI(reserva:Reserva){
    val alcanceCorrutina = rememberCoroutineScope()
    val contexto = LocalContext.current
    val resources = contexto.resources
    val (costo, setCosto) = remember { mutableStateOf(0)   }

    LaunchedEffect(Unit ){
        withContext(Dispatchers.IO){
              val service =    Factory.getCostService()
              setCosto(service.buscar("2023-09-15T03:00:00.000Z").valor)
        }


    }

    Row {
        Box (modifier = Modifier.height(200.dp)){
            Image(painter = painterResource(id = R.drawable.viajar), contentDescription ="ImagenLugar")
        }
        Column {
            Row {
                Text(text = "Nombre Lugar  ",modifier= Modifier.weight(2f))
                Text(text = reserva.lugar)
            }
            Row {
                
                Text(text = "Costo x Noche  ",modifier= Modifier.weight(2f))
                Text(text = (reserva.costoAlojamiento/costo  ).toString())
            }
            Row {
                Text(text = "Traslado")
                Text(text = (reserva.costoTraslado/costo).toString())
            }
            Row {
               // iconos eliminar , modficar
                Icon( Icons.Filled.LocationOn, contentDescription ="localizacion",modifier = Modifier.clickable {  } )
                Icon( Icons.Filled.Edit, contentDescription ="editar",modifier = Modifier.clickable {  } )
                Icon( Icons.Filled.Delete, contentDescription ="Borrar",modifier = Modifier.clickable {  }  )
            } 
            

            }
        }
        
}

@Composable
fun largeUniqueItemUI(){
    




}
    
    
    


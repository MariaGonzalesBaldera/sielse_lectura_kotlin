package com.app.sielseapplecturaskotlin.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.sielseapplecturaskotlin.R
import com.app.sielseapplecturaskotlin.navigation.AppScreens
import com.app.sielseapplecturaskotlin.view.component.ListDetail
import kotlinx.coroutines.launch

@Preview
@Composable
private fun ShowPrev() {
  //ScreenListBooks()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenListBooks(navControllerPrincipal: NavController) {
  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val coroutineScope = rememberCoroutineScope()

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      MenuDrawer(
        modifier = Modifier.requiredWidth(280.dp),
        context = LocalContext.current,
        descargarPadron = { context -> /* Implement your download logic */ },
        descargarSupervisionProgramada = { context -> /* Implement your download logic */ },
        descargarSupervisionNoProgramada = { context -> /* Implement your download logic */ },
        enviarLecturas = { context -> /* Implement your send logic */ },
        enviarFotos = { context -> /* Implement your send logic */ },
        borrarDatos = { context -> /* Implement your delete logic */ }
      )
    }
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.primary),
            titleContentColor = colorResource(id = R.color.white)
          ),
          title = { Text("LIBROS PENDIENTES", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
          navigationIcon = {
            IconButton(onClick = {
              coroutineScope.launch {
                drawerState.open()
              }
            }) {
              Icon(
                Icons.Default.Menu,
                contentDescription = "Menu",
                tint = colorResource(id = R.color.white)
              )
            }
          },
        )
      }
    ) { paddingValues: PaddingValues ->
      // Main content goes here
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues)
      ) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "screen1") {
          composable("screen1") { Screen1(navController) }
          composable("screen2") { Screen2(navController) }
          composable("screen3") { Screen3(navControllerPrincipal) }

        }
      }
    }
  }
}
@Composable
fun Screen1(navController: NavController) {
  ItemListBooks { navController.navigate("screen2") }
}

@Composable
fun Screen2(navController: NavController) {
  ListDetail() { navController.navigate("screen3") }
}
@Composable
fun Screen3(navControllerPrincipal:NavController) {
  ScreenReading(navControllerPrincipal) //{ navController.popBackStack() }
}
@Composable
fun MenuDrawer(
  modifier: Modifier,
  context: Context,
  descargarPadron: suspend (Context) -> Unit,
  descargarSupervisionProgramada: suspend (Context) -> Unit,
  descargarSupervisionNoProgramada: suspend (Context) -> Unit,
  enviarLecturas: suspend (Context) -> Unit,
  enviarFotos: suspend (Context) -> Unit,
  borrarDatos: suspend (Context) -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  Column(
    modifier = modifier
      .fillMaxHeight()
      .padding(top = 65.dp)
      .background(colorResource(id = R.color.white))
  ) {
    // Drawer header
    Text(
      text = "Menú de Opciones",
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(id = R.color.grey))
        .padding(16.dp),
      style = MaterialTheme.typography.bodySmall,
      color = Color.Black
    )

    // Download Data section
    ExpansionMenuItem(
      title = "Descargar Datos",
      items = listOf(
        "Padrón de Lecturas" to { coroutineScope.launch { descargarPadron(context) } },
        "Supervisión Programada" to { coroutineScope.launch { descargarSupervisionProgramada(context) } },
        "Supervisión No Programada" to {
          coroutineScope.launch {
            descargarSupervisionNoProgramada(
              context
            )
          }
        }
      )
    )

    // Send Data section
    ExpansionMenuItem(
      title = "Enviar Datos",
      items = listOf(
        "Enviar Lecturas" to { coroutineScope.launch { enviarLecturas(context) } },
        "Enviar Fotos" to { coroutineScope.launch { enviarFotos(context) } }
      )
    )

    // Other section
    ExpansionMenuItem(
      title = "Otros",
      items = listOf(
        "Borrar datos" to { coroutineScope.launch { borrarDatos(context) } }
      )
    )
  }
}
@Composable
fun ExpansionMenuItem(
  title: String,
  items: List<Pair<String, () -> Unit>>
) {
  var expanded by remember { mutableStateOf(false) }

  // Animación para el icono de expansión
  val iconRotation by animateFloatAsState(if (expanded) 180f else 0f)

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .animateContentSize() // Animación de contenido
  ) {
    // Título de la sección expandible con icono
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { expanded = !expanded }
        .padding(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Texto del título con cambio de color
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = if (expanded) colorResource(id = R.color.primary) else Color.Black,
        modifier = Modifier.weight(1f)
      )

      // Icono que rota cuando se expande
      Icon(
        imageVector = Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        modifier = Modifier.rotate(iconRotation),
        tint = if (expanded) colorResource(id = R.color.primary) else Color.Black
      )
    }

    // Elementos del menú cuando está expandido
    if (expanded) {
      items.forEach { (itemTitle, onClick) ->
        Text(
          text = itemTitle,
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
        )
      }
    }
  }
}

///itemListBooks

@Composable
  fun ItemListBooks(onClick: () -> Unit){
  Box (modifier = Modifier
    .clickable { onClick() }
    .fillMaxWidth()
    .padding(horizontal = 15.dp, vertical = 8.dp)){
    Column {
      Text(text = "0010627 > TUPAC AMARU - RINCONADA(942)", fontSize = 14.sp, fontWeight = FontWeight.Bold)
      Text(text = "Lecturados: 0 (enviadas 0)", color = colorResource(id = R.color.grey_dark))
      Text(text = "Fotos: 2(enviadas 0)", color = colorResource(id = R.color.grey_dark))
      HorizontalDivider(modifier=Modifier.padding(top=10.dp))

    }

  }
}
package com.app.sielseapplecturaskotlin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.sielseapplecturaskotlin.R
import com.app.sielseapplecturaskotlin.view.component.BotonDefault
import com.app.sielseapplecturaskotlin.view.component.ButtonSystem

@Preview(showBackground = true)
@Composable
fun ScreenReading() {
  var expanded by remember { mutableStateOf(false) }
  val items = listOf("ruta", "suministro", "medidor")
  var selectedText by remember { mutableStateOf(items[0]) }

  Scaffold (
    content = { paddingValues ->
      Column(modifier = Modifier.fillMaxSize()) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
          modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
        ) {
          ButtonSystem(title = "Fotos", onClick = {})
          ButtonSystem(title = "Mapa", onClick = {})
        }
        Column(modifier = Modifier.padding(10.dp)) {
          Text(text = "RUTA:                   000000000000000000")
          Text(text = "NOMBRE:            000000000000000000")
          Text(text = "DIRECCIÓN:        000000000000000000")
          Text(text = "MEDIDOR:           000000000000000000")
        }
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
          Text(text = "valor de lectura .")

          Box(
            modifier = Modifier
              .wrapContentSize(Alignment.Center)
              .padding(top = 20.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .clickable { expanded = true }
                .padding(8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween // Espacia entre el texto y el icono
            ) {
              Text(
                text = selectedText, // Mostrar el texto seleccionado
                modifier = Modifier.weight(1f) // Ocupa el espacio disponible
              )

              Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
              )
            }

            // Menú desplegable
            DropdownMenu(
              modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.grey_light)),
              expanded = expanded,
              onDismissRequest = { expanded = false }
            ) {
              items.forEach { item ->
                DropdownMenuItem(
                  text = { Text(item) },
                  onClick = {
                    selectedText = item // Actualiza el texto seleccionado
                    expanded = false // Cierra el menú
                  }
                )
              }
            }
          }
        }
        Column(
          horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
        ) {
          val saveIcon = painterResource(id = R.drawable.ic_save)
          Text(text = "Requiere lectura", color = Color.Red)
          Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 100.dp)) {
            BotonDefault(
              title = "GUARDAR",
              onClick = { /*TODO*/ },
              icon = saveIcon)

          }
        }
      }
    }
  )
}

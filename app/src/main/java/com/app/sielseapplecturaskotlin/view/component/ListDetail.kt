package com.app.sielseapplecturaskotlin.view.component

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.sielseapplecturaskotlin.R

@Composable
fun ListDetail(onClick: () -> Unit) {
  var isChecked by remember { mutableStateOf(false) }
  var expanded by remember { mutableStateOf(false) }
  val items = listOf("ruta", "suministro", "medidor")
  var selectedText by remember { mutableStateOf(items[0]) }

  Column(modifier = Modifier.fillMaxSize() ) {
    Row(modifier = Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
      Text(text = "Buscar: ", fontSize = 16.sp)
      Box(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Row(
          modifier = Modifier
            .width(150.dp)
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
            .width(150.dp)
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
      }    }
    Box(
      modifier = Modifier
        .clickable {onClick() } //onClick()
       // .clickable { isChecked = !isChecked }
        .fillMaxWidth()
        .border(1.dp, colorResource(id = R.color.primary))
        .padding(vertical = 8.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Checkbox(
          checked = isChecked,
          onCheckedChange = { isChecked = it }
        )
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "10010764933 - 0010627000079", fontSize = 14.sp, fontWeight = FontWeight.Bold)
          Text(text = "[SECTOR CHACAHUAYCO S/N]", color = colorResource(id = R.color.grey_dark))
          Text(text = "[QUISPE YAURI, BALVINO]", color = colorResource(id = R.color.grey_dark))
          Text(text = "[2020372749/CSZ/DDS720]", color = colorResource(id = R.color.grey_dark))
        }
      }
    }
  }
}

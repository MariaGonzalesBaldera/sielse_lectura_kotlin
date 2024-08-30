package com.app.sielseapplecturaskotlin.view

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.sielseapplecturaskotlin.R
import com.app.sielseapplecturaskotlin.navigation.AppScreens
import com.app.sielseapplecturaskotlin.utils.toast
import com.app.sielseapplecturaskotlin.view.component.BotonDefault
import com.app.sielseapplecturaskotlin.view.component.ButtonSystem
import com.app.sielseapplecturaskotlin.view.component.ProgressDialogLoading
import com.app.sielseapplecturaskotlin.view.component.TransparentTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenReading(navController: NavController) {
  var expanded by remember { mutableStateOf(false) }
  val items = listOf(
    "OK",
    "DESCENTRADO SIN LECTURA",
    "NO DISPLAYA",
    "IMPEDIMEN SIN LECTURA",
    "SERIE ERRADA",
    "CAJA FUERTE CON LECTURA",
    "LECTURA VERIFICADA",
    "INTERIOR SIN LECTURA",
    "TAPA/VISOR DAÑADO",
    "SIN MEDIDOR",
    "CDIRECTA CON LECTURA",
    "NO UBICADO",
    "MEDIDOR DAÑADO",
    "INTERIOR SIN LECTURA",
    "TAPA/VISOR DAÑADO",
    "SIN MEDIDOR",
    "CDIRECTA CON LECTURA",
    "NO UBICADO",
    "MEDIDOR DAÑADO"
  )
  var selectedText by remember { mutableStateOf(items[0]) }
  val isValueValid = remember { mutableStateOf(true) }
  val valueValue = remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current

  Scaffold(
    bottomBar = {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 10.dp)
      ) {
        ButtonSystem(
          title = "ANTERIOR",
          onClick = {},
          widthValue = 180.dp,
          colorValue = colorResource(id = R.color.green)
        )
        ButtonSystem(
          title = "SIGUIENTE",
          onClick = {},
          widthValue = 180.dp,
          colorValue = colorResource(id = R.color.green)
        )
      }
    },
    content = { paddingValues ->
      Column(
        modifier = Modifier.fillMaxSize()
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(text = "Obteniendo posición", color = Color.Red)
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
          modifier = Modifier
            .fillMaxWidth()
        ) {
          ButtonSystem(
            title = "Fotos",
            onClick = {
              navController.navigate(route = AppScreens.ScreenPhoto.route)
            },
            widthValue = 140.dp,
            colorValue = colorResource(id = R.color.primary)
          )
          ButtonSystem(
            title = "Mapa",
            onClick = {
              navController.navigate(route = AppScreens.LocationScreen.route)
            },
            widthValue = 140.dp,
            colorValue = colorResource(id = R.color.primary)
          )
        }
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
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
          TransparentTextField(
            textFieldValue = valueValue,
            textLabel = "valor de lectura",
            keyboardType = KeyboardType.Number,
            keyboardActions = KeyboardActions(
              onNext = {
                focusManager.moveFocus(FocusDirection.Down)
              }
            ),
            imeAction = ImeAction.None,
            isValid = isValueValid
          )
          Box(
            modifier = Modifier
              .wrapContentSize(Alignment.Center)
              .padding(top = 20.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .border(1.dp, Color.Gray)
                .padding(10.dp)
                .clickable { expanded = true },
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
              onClick = { },
              icon = saveIcon
            )
          }
        }
      }
    }
  )
}

package com.app.sielseapplecturaskotlin.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.app.sielseapplecturaskotlin.R

@Composable
fun TransparentTextField(
  modifier: Modifier = Modifier,
  textFieldValue: MutableState<String>,
  textLabel: String,
  maxChar: Int? = null,
  capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
  keyboardType: KeyboardType,
  keyboardActions: KeyboardActions,
  imeAction: ImeAction,
  trailingIcon: @Composable() (() -> Unit)? = null,
  visualTransformation: VisualTransformation = VisualTransformation.None
) {
  var isValid by remember { mutableStateOf(true) }
  val errorColor = Color.Red
  val primaryColor = colorResource(id = R.color.purple)

  OutlinedTextField(
    modifier = modifier
      .fillMaxWidth()
      .padding(start = 10.dp, end = 10.dp),
    value = textFieldValue.value.take(maxChar ?: 40),
    onValueChange = { value ->
      textFieldValue.value = value
      isValid = value.isNotEmpty()
    },
    label = {
      Text(text = textLabel)
    },
    isError = !isValid,
    trailingIcon = trailingIcon,
    keyboardOptions = KeyboardOptions(
      capitalization = capitalization,
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    keyboardActions = keyboardActions,
    visualTransformation = visualTransformation,

  )
  if (!isValid) {
    Text(
      text = "Please enter valid text", color = errorColor,
      modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    )
  }
}
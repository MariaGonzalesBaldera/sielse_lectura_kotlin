package com.app.sielseapplecturaskotlin.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.sielseapplecturaskotlin.R

@Composable
fun ButtonSystem(
  modifier: Modifier = Modifier,
  title: String,
  onClick: () -> Unit,
  widthValue: Dp,
  colorValue: Color
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .clickable { onClick() }
      .width(widthValue)
      .height(40.dp)
      .background(color = colorValue, shape = RoundedCornerShape(10))
  ) {
    Text(text = title, color = Color.White)
  }
}
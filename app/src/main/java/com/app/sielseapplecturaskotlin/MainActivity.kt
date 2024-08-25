package com.app.sielseapplecturaskotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.app.sielseapplecturaskotlin.navigation.AppNavigation
import com.app.sielseapplecturaskotlin.view.LoginComponent
import com.app.sielseapplecturaskotlin.viewModel.LecturaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val lecturaViewModel: LecturaViewModel by viewModels()
      AppNavigation(lecturaViewModel)
    }
  }
}

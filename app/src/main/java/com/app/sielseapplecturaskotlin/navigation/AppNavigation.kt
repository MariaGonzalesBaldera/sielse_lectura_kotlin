package com.app.sielseapplecturaskotlin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.sielseapplecturaskotlin.view.LoginComponent
import com.app.sielseapplecturaskotlin.viewModel.LecturaViewModel

@Composable
fun AppNavigation(
  lecturaViewModel:LecturaViewModel){
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route){
    composable(route = AppScreens.LoginScreen.route) {
      LoginComponent( lecturaViewModel = lecturaViewModel)
    }
  }
}
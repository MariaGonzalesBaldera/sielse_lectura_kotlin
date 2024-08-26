package com.app.sielseapplecturaskotlin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.sielseapplecturaskotlin.view.ItemListBooks
import com.app.sielseapplecturaskotlin.view.LoginComponent
import com.app.sielseapplecturaskotlin.view.ScreenListBooks
import com.app.sielseapplecturaskotlin.view.ScreenReadingList
import com.app.sielseapplecturaskotlin.view.component.ListDetail
import com.app.sielseapplecturaskotlin.viewModel.LecturaViewModel

@Composable
fun AppNavigation(
  lecturaViewModel: LecturaViewModel
) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
    composable(route = AppScreens.LoginScreen.route) {
      LoginComponent(navController = navController, lecturaViewModel = lecturaViewModel)
    }
    composable(route = AppScreens.ScreenListBooks.route) {
      ScreenListBooks(navController = navController)
    }
    composable(route = AppScreens.ScreenReadingList.route) {
      ScreenReadingList()
    }


  }
}
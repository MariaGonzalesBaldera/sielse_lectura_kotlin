package com.app.sielseapplecturaskotlin.navigation


sealed class AppScreens(val route: String) {
  object LoginScreen:AppScreens("login_screen")
}
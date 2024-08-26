package com.app.sielseapplecturaskotlin.navigation


sealed class AppScreens(val route: String) {
  object LoginScreen:AppScreens("loginScreen")
  object ScreenListBooks:AppScreens("ScreenListBooks")
  object ScreenReadingList:AppScreens("ScreenReadingList")
  object ListDetail:AppScreens("ListDetail")
}
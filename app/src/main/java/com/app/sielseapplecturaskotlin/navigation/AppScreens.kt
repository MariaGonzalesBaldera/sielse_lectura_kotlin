package com.app.sielseapplecturaskotlin.navigation


sealed class AppScreens(val route: String) {
  object LoginScreen:AppScreens("loginScreen")
  object ScreenListBooks:AppScreens("ScreenListBooks")
  object ScreenReadingList:AppScreens("ScreenReadingList")
  object ListDetail:AppScreens("ListDetail")
  object ScreenPhoto:AppScreens("ScreenPhoto")
  object LocationScreen:AppScreens("LocationScreen")
  object LocationMap:AppScreens("LocationMap")
}
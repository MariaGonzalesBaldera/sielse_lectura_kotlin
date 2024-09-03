package com.app.sielseapplecturaskotlin.data.db.entity

data class User(
  val login: String? = "",
  val password: String? = "",
  val empresa: String? = "",
  val urlServicio: String? = "",
  val verSoloPendientes: Boolean? = false,
  val envioLecturaAutomatico: Boolean? = false,
  val envioFotoAutomatico: Boolean? = false,
)

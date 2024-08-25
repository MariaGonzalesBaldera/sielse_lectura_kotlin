package com.app.sielseapplecturaskotlin.data.database.entity

data class Autenticacion(
  val loginTrabajador: String = "",
  val nombreTrabajador: String = "",
  val usuarioValido: Boolean = false,
  val mensajeResultado: String = "",
)

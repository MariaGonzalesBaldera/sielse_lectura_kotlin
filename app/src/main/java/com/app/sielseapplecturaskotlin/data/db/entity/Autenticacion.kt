package com.app.sielseapplecturaskotlin.data.db.entity

data class Autenticacion(
  val loginTrabajador: String = "",
  val nombreTrabajador: String = "",
  val usuarioValido: Boolean = false,
  val mensajeResultado: String = "",
)

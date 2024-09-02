package com.app.sielseapplecturaskotlin.data.database.entity

import com.google.android.gms.maps.model.LatLng

data class PinSuministro(
  val codigoSuministro: Int,
  val nombreSuministro: String,
  val rutaSuministro: String,
  val medidor: String,
  val posicionSuministro: LatLng,
  val lecturado: Boolean
) {
  companion object {
    fun fromSuministro(suministro: Suministro): PinSuministro {
      return PinSuministro(
        codigoSuministro = suministro.codigoSuministro,
        nombreSuministro = suministro.nombreSuministro,
        rutaSuministro = suministro.rutaSuministro,
        medidor = suministro.medidor,
        posicionSuministro = LatLng(suministro.latitudSuministro, suministro.longitudSuministro),
        lecturado = suministro.fechaLectura != null
      )
    }
  }
}
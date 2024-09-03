package com.app.sielseapplecturaskotlin.data.db.entity

import com.google.android.gms.maps.model.LatLng

data class Lectura(
  var codigoSuministro: String,
  var codigoLibro: Int,
  var rutaSuministro: String,
  var nombreSuministro: String,
  var direccionSuministro: String,
  var medidor: String,
  var lectura: Int?,
  var valorReingreso: String,
  var observacion: Int,
  var fechaLectura: String?,
  var posicionActual: LocationData?,
  var coordenadas: LatLng,
  var lecturaAnterior: Int,
  var rangoInferior: Int,
  var rangoSuperior: Int,
  var rangoInferiorFoto: Int,
  var rangoSuperiorFoto: Int,
  var fotoObligatoria: Boolean,
  var secuenciaPropuesta: Int,
  var secuenciaEjecutada: Int,
  var lecturaEnviada: Boolean
) {

  companion object {
    fun fromSuministro(suministro: Suministro): Lectura {
      return Lectura(
        codigoSuministro = suministro.codigoSuministro.toString(),
        codigoLibro = suministro.codigoLibro,
        rutaSuministro = suministro.rutaSuministro,
        nombreSuministro = suministro.nombreSuministro,
        direccionSuministro = suministro.direccionSuministro,
        medidor = suministro.medidor,
        lectura = suministro.lectura,
        valorReingreso = "",
        observacion = suministro.observacion,
        fechaLectura = suministro.fechaLectura,
        posicionActual = null,
        coordenadas = LatLng(suministro.latitudSuministro, suministro.longitudSuministro),
        lecturaAnterior = suministro.lecturaAnterior,
        rangoInferior = suministro.rangoInferior,
        rangoSuperior = suministro.rangoSuperior,
        rangoInferiorFoto = suministro.rangoInferiorFoto,
        rangoSuperiorFoto = suministro.rangoSuperiorFoto,
        fotoObligatoria = suministro.fotoObligatoria,
        secuenciaPropuesta = suministro.secuenciaPropuesta,
        secuenciaEjecutada = suministro.secuenciaEjecutada,
        lecturaEnviada = suministro.lecturaEnviada
      )
    }
  }

  fun tomarFotoObligatoriamente(): Boolean {
    return fotoObligatoria || fueraDeRangoFoto()
  }

  fun fueraDeRango(): Boolean {
    return lectura?.let { it < rangoInferior || it > rangoSuperior } ?: false
  }

  fun fueraDeRangoFoto(): Boolean {
    return lectura?.let { it < rangoInferiorFoto || it > rangoSuperiorFoto } ?: false
  }

  fun consumoCero(): Boolean {
    return lectura?.let { it <= lecturaAnterior } ?: false
  }

  fun evaluarValorReingreso(reingreso: String): Boolean {
    return lectura?.let { reingreso == it.toString().reversed() } ?: false
  }
}

data class LocationData(
  val latitude: Double,
  val longitude: Double,
  val accuracy: Float,
  val altitude: Double,
  val bearing: Float,
  val speed: Float,
  val time: Long
)

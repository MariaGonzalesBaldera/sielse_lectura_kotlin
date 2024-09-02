package com.app.sielseapplecturaskotlin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "suministros",
  foreignKeys = [
    ForeignKey(
      entity = Libro::class,
      parentColumns = ["codigo"],
      childColumns = ["codigoLibro"],
      onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
      entity = Observacion::class,
      parentColumns = ["codigo"],
      childColumns = ["observacion"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index(value = ["codigoLibro"]), Index(value = ["observacion"])]
)data class Suministro(
  @PrimaryKey
  @ColumnInfo(name = "codigoSuministro") val codigoSuministro: Int,
  @ColumnInfo(name = "codigoLibro") val codigoLibro: Int,
  @ColumnInfo(name = "rutaSuministro") val rutaSuministro: String,
  @ColumnInfo(name = "nombreSuministro") val nombreSuministro: String,
  @ColumnInfo(name = "direccionSuministro") val direccionSuministro: String,
  @ColumnInfo(name = "medidor") val medidor: String,
  @ColumnInfo(name = "periodo") val periodo: Int,
  @ColumnInfo(name = "codigoZonaAdministrativa") val codigoZonaAdministrativa: Int,
  @ColumnInfo(name = "lectura") val lectura: Int,
  @ColumnInfo(name = "observacion") val observacion: Int,
  @ColumnInfo(name = "fechaLectura") val fechaLectura: String,
  @ColumnInfo(name = "latitudSuministro") val latitudSuministro: Double,
  @ColumnInfo(name = "longitudSuministro") val longitudSuministro: Double,
  @ColumnInfo(name = "lecturaAnterior") val lecturaAnterior: Int,
  @ColumnInfo(name = "rangoInferior") val rangoInferior: Int,
  @ColumnInfo(name = "rangoSuperior") val rangoSuperior: Int,
  @ColumnInfo(name = "rangoInferiorFoto") val rangoInferiorFoto: Int,
  @ColumnInfo(name = "rangoSuperiorFoto") val rangoSuperiorFoto: Int,
  @ColumnInfo(name = "fotoObligatoria") val fotoObligatoria: Boolean,
  @ColumnInfo(name = "secuenciaPropuesta") val secuenciaPropuesta: Int,
  @ColumnInfo(name = "secuenciaEjecutada") val secuenciaEjecutada: Int,
  @ColumnInfo(name = "lecturaEnviada") val lecturaEnviada: Boolean,

  )

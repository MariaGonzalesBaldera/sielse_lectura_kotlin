package com.app.sielseapplecturaskotlin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
  tableName = "fotos",
  primaryKeys = ["codigoSuministro", "numeroFoto"],
  foreignKeys = [
    ForeignKey(
      entity = Suministro::class,
      parentColumns = ["codigoSuministro"],
      childColumns = ["codigoSuministro"],
      onDelete = ForeignKey.CASCADE
    )
  ]
)data class Foto(
  @ColumnInfo(name = "codigoSuministro") val codigoSuministro: Int,
  @ColumnInfo(name = "numeroFoto") val numeroFoto: Int,
  val foto: String,
  @ColumnInfo(name = "fotoEnviada") val fotoEnviada: Boolean
)

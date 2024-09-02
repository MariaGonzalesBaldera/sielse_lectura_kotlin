package com.app.sielseapplecturaskotlin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "observacions")
data class Observacion(
  @PrimaryKey
  @ColumnInfo(name = "codigo") val codigo: Int,
  @ColumnInfo(name = "nombre") val nombre: String,
  @ColumnInfo(name = "requiereLectura") val requiereLectura: Boolean,
  @ColumnInfo(name = "requiereFoto") val requiereFoto: Boolean,
  @ColumnInfo(name = "solicitar_Ringreso") val solicitarReingreso: Boolean,
  @ColumnInfo(name = "tipo") val tipo: Int,
  @ColumnInfo(name = "color") val color: Int,
  )

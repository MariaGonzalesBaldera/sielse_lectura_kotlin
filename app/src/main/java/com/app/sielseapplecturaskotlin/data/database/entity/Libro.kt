package com.app.sielseapplecturaskotlin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros")
data class Libro(
  @PrimaryKey
  @ColumnInfo(name = "codigo") val codigo: Int,
  @ColumnInfo(name = "tipo") val tipo: Int,
  @ColumnInfo(name = "nombreTipo") val nombreTipo: String,
  @ColumnInfo(name = "ruta") val ruta: String,
  @ColumnInfo(name = "nombre") val nombre: String,
  @ColumnInfo(name = "total") val total: Int,
  @ColumnInfo(name = "totalCompletado") val totalCompletado: Int,
  @ColumnInfo(name = "digitosSuministro") val digitosSuministro: Int,
  @ColumnInfo(name = "sucursal") val sucursal: Int,
)

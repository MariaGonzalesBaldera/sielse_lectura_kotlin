package com.app.sielseapplecturaskotlin.data.api.dto

import com.google.gson.annotations.SerializedName

data class Empresa(
  @SerializedName("a") val a: Int,
  @SerializedName("b") val nombre: String,
  @SerializedName("c") val url: String
)

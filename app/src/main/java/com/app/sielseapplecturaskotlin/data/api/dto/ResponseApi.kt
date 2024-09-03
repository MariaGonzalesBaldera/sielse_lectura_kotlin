package com.app.sielseapplecturaskotlin.data.api.dto

import com.google.gson.annotations.SerializedName

data class ResponseApi(
  @SerializedName("operacion") val operacion: Operacion
)

data class Operacion(
  @SerializedName("a") val a: Boolean,
  @SerializedName("b") val b: String
)
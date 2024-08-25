package com.app.sielseapplecturaskotlin.data.dto

data class OperacionResult(
  val resultado: Boolean,
  val datos: String
) {
  companion object {
    fun fromJson(json: Map<String, Any>): OperacionResult {
      return OperacionResult(
        resultado = json["a"] as Boolean,
        datos = json["b"] as String
      )
    }
  }
}

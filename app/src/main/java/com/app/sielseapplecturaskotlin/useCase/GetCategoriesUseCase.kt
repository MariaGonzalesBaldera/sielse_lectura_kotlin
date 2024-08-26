package com.app.sielseapplecturaskotlin.useCase

import android.content.Context
import android.util.Log
import com.app.sielseapplecturaskotlin.data.database.entity.Autenticacion
import com.app.sielseapplecturaskotlin.data.database.entity.User
import com.app.sielseapplecturaskotlin.data.dto.Empresa
import com.app.sielseapplecturaskotlin.data.dto.OperacionResult
import com.app.sielseapplecturaskotlin.data.repository.QuoteRepository
import com.app.sielseapplecturaskotlin.utils.isConnected
import com.app.sielseapplecturaskotlin.utils.toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
  private val repository: QuoteRepository
) {

  suspend fun getCategories(): List<Empresa>? {
    val response = repository.getCateriesRepository()
    return if (response.isSuccessful) {
      val operacion = response.body()?.operacion
      operacion?.let {
        val empresasJson = it.b
        val gson = Gson()

        val type = object : TypeToken<Map<String, List<Empresa>>>() {}.type
        val empresaMap: Map<String, List<Empresa>> = gson.fromJson(empresasJson, type)
        empresaMap["Empresa"]
      }
    } else {
      Log.e("ERROR", response.errorBody().toString())
      null
    }
  }

  suspend fun getAuthentication(context: Context, user: String, password: String):Boolean {
    if (isConnected(context)) {
      val response = repository.getAuthentication(user, password)
      if (response.isSuccessful) {
        val result = response.body()?.operacion
        if (result?.a == false) {
          Log.e("Error", "Error login")
          return false
        } else {
          val datosJson: Map<String, Any> =
            Gson().fromJson(result?.b, object : TypeToken<Map<String, Any>>() {}.type)
          val usuarioJson = datosJson["Usuario"] as Map<String, Any>

          val isValid = usuarioJson["c"] as Boolean

          if (isValid) {
            val user = Autenticacion(
              loginTrabajador = usuarioJson["a"] as String,
              nombreTrabajador = usuarioJson["b"] as String,
              usuarioValido = usuarioJson["c"] as Boolean,
              mensajeResultado = usuarioJson["d"] as String
            )
            return true

          }
          return false
        }
      } else {
        Log.e("ERROR", response.errorBody()?.string().orEmpty())
        return false
      }
    } else {
      context.toast("Conectese a internet")
      return false
    }

  }

}
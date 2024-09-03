package com.app.sielseapplecturaskotlin.data.repository

import android.content.Context
import android.util.Log
import com.app.sielseapplecturaskotlin.data.api.QuoteService
import com.app.sielseapplecturaskotlin.data.api.dto.ResponseApi
import com.app.sielseapplecturaskotlin.data.db.dao.LecturaDao
import com.app.sielseapplecturaskotlin.data.db.entity.Autenticacion
import com.app.sielseapplecturaskotlin.utils.isConnected
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(
  private val api: QuoteService,
  private val dao: LecturaDao
){
  suspend fun getCateriesRepository(): Response<ResponseApi> {
    val response: Response<ResponseApi> = api.getListCategories()
    Log.e("get categories", "repository " + response.message())
    return response
  }

  suspend fun getAuthentication(user:String, password:String,context:Context): Boolean {
    if(!isConnected(context)){
      return false
    }else{
      val response = api.authenticationServices(user, password)
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

    }
  }
}
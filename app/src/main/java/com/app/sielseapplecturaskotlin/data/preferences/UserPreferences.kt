package com.app.sielseapplecturaskotlin.data.preferences

import android.content.SharedPreferences
import com.app.sielseapplecturaskotlin.data.db.entity.User
import javax.inject.Inject

class UserPreferences @Inject constructor(
  private val sharedPreferences: SharedPreferences
) {
  companion object {
    private const val LOGIN_KEY = "login"
    private const val EMPRESA_KEY = "empresa"
    private const val PASSWORD_KEY = "password"
    private const val URL_SERVICIO_KEY = "url_servicio"
    private const val VER_SOLO_PEDIENTES_KEY = "ver_solo_pendientes"
    private const val ENVIO_LECTURAS_AUTO_KEY = "envio_lecturas_auto"
    private const val ENVIO_FOTO_AUTO_KEY = "envio_foto_auto"
  }

  fun saveUserCredentials(
    login: String,
    empresa: String,
    password: String,
    urlServicio: String,
    verSoloPendientes: Boolean,
    envioLecturasAuto: Boolean,
    envioFotoAuto: Boolean,
  ) {
    with(sharedPreferences.edit()) {
      putString(LOGIN_KEY, login)
      putString(EMPRESA_KEY, empresa)
      putString(PASSWORD_KEY, password)
      putString(URL_SERVICIO_KEY, urlServicio)
      putBoolean(VER_SOLO_PEDIENTES_KEY, verSoloPendientes)
      putBoolean(ENVIO_LECTURAS_AUTO_KEY, envioLecturasAuto)
      putBoolean(ENVIO_FOTO_AUTO_KEY, envioFotoAuto)
      apply()
    }
  }

  fun getUserCredentials(): User? {
    val login = sharedPreferences.getString(LOGIN_KEY, null)
    val empresa = sharedPreferences.getString(EMPRESA_KEY, null)
    val password = sharedPreferences.getString(PASSWORD_KEY, null)
    val urlServicio = sharedPreferences.getString(URL_SERVICIO_KEY, null)
    val verSoloPendientes = sharedPreferences.getBoolean(VER_SOLO_PEDIENTES_KEY, false)
    val envioLecturasAuto = sharedPreferences.getBoolean(ENVIO_LECTURAS_AUTO_KEY, true)
    val envioFotoAuto = sharedPreferences.getBoolean(ENVIO_FOTO_AUTO_KEY, true)

    return if (login != null && empresa != null && password != null && urlServicio != null) {
      User(
        login = login,
        empresa = empresa,
        password = password,
        urlServicio = urlServicio,
        verSoloPendientes = verSoloPendientes,
        envioLecturaAutomatico = envioLecturasAuto,
        envioFotoAutomatico = envioFotoAuto,
      )
    } else null
  }

  fun clearUserCredentials() {
    with(sharedPreferences.edit()) {
      remove(LOGIN_KEY)
      remove(EMPRESA_KEY)
      remove(PASSWORD_KEY)
      remove(URL_SERVICIO_KEY)
      remove(VER_SOLO_PEDIENTES_KEY)
      remove(ENVIO_LECTURAS_AUTO_KEY)
      remove(ENVIO_FOTO_AUTO_KEY)
      apply()
    }
  }
}
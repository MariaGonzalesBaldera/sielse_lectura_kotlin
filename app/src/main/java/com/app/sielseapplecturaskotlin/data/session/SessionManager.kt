package com.app.sielseapplecturaskotlin.data.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
  private var empresa: String = ""
  private var urlServicio: String = "appsrvtest.else.com.pe"
  private var login: String = ""
  private var password: String = ""
  private var apiName: String = "WapiSielseMovilV11"

  fun saveEmpresa(newEmpresa: String) {
    empresa = newEmpresa
  }

  fun getEmpresa(): String {
    return empresa
  }
  fun saveUrlServicio(newUrlServicio: String) {
    urlServicio = newUrlServicio
  }

  fun getUrlServicio(): String {
    return urlServicio
  }
  fun saveLogin(newLogin: String) {
    login = newLogin
  }

  fun getLogin(): String {
    return login
  }
  fun savePassword(newPassword: String) {
    password = newPassword
  }

  fun getPassword(): String {
    return password
  }

  fun getApiName(): String {
    return apiName
  }
}
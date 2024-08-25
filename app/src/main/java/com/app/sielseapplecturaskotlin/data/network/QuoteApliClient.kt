package com.app.sielseapplecturaskotlin.data.network

import com.app.sielseapplecturaskotlin.data.dto.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface QuoteApliClient {

  @GET("seguridad/getListadoEmpresas")
  suspend fun listCategories(
    @Header("Authorization") authorization: String,
  ): Response<ResponseApi>

  @GET("seguridad/autenticacionRemotaLecturas")
  suspend fun authentication(
    @Header("Authorization") authorization: String,
    @Query("Usuario") user: String,
    @Query("Password") password: String,
    @Query("version") version: String
  ): Response<ResponseApi>
}
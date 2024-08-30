package com.app.sielseapplecturaskotlin.data.network

import com.app.sielseapplecturaskotlin.data.dto.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface QuoteApliClient {
  //proveedor datos http
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

  @GET("/lecturas/GetObservaciones")
  suspend fun listaObservacioes(
    @Header("Authorization") authorization: String,
    @Query("login") login: String
  ): Response<ResponseApi>

  @GET("/lecturas/getlibrosporusuario")
  suspend fun librosPorCronograma(
    @Header("Authorization") authorization: String,
    @Query("login") login: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetLecturasPorLibroUsuario")
  suspend fun suministrosPorLibroCronograma(
    @Header("Authorization") authorization: String,
    @Query("login") login: String,
    @Query("libro") libro: String,
    @Query("sucursal") sucursal: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetLibrosSupervisionProgramadaPorUsuario")
  suspend fun librosSupervisionProgramada(
    @Header("Authorization") authorization: String,
    @Query("login") login: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetLecturasSupervisionProgramadaPorLibroUsuario")
  suspend fun suministrosPorLibroSupervisionProgramada(
    @Header("Authorization") authorization: String,
    @Query("login") login: String,
    @Query("libro") libro: String,
    @Query("sucursal") sucursal: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetZonasPorSupervisor")
  suspend fun zonaProgramada(
    @Header("Authorization") authorization: String,
    @Query("login") login: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetDatosLibroSupervision")
  suspend fun libroPorRuta(
    @Header("Authorization") authorization: String,
    @Query("login") login: String,
    @Query("libro") libro: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetLecturasLibroPorSupervisor")
  suspend fun suministrosPorSupervisorLibro(
    @Header("Authorization") authorization: String,
    @Query("login") login: String,
    @Query("sucursal") sucursal: String,
    @Query("zona") zona: String,
    @Query("libro") libro: String
  ): Response<ResponseApi>

  @GET("/lecturas/GetObservaciones")
  suspend fun fechaHoraServidor(
    @Header("Authorization") authorization: String,
    @Query("login") login: String,
  ): Response<ResponseApi>

  //despachador datos http

  @GET("/lecturas/enviarLectura")
  suspend fun enviarLectura(
    @Header("Authorization") authorization: String,
    @Query("login") login:String,
    @Query("codigoSuministro") codigoSuministro:String,
    @Query("tipo") tipo:String,
    @Query("lectura") lectura:String,
    @Query("observacion") observacion:String,
    @Query("secuenciaEjecutada") secuenciaEjecutada:String,
    @Query("latitud") latitud:String,
    @Query("longitud") longitud:String,
    @Query("fecha") fecha:String,   //fechaLectura.replaceAll(' ', '%20')
    @Query("sucursal") sucursal:String,
    @Query("etiqueta1Valor") etiqueta1Valor:String,     //app_version_2
    @Query("codigoPeriodoComercial") codigoPeriodoComercial:String,
    @Query("codigoSucursal") codigoSucursal:String,
    @Query("codigoZonaAdministrativa") codigoZonaAdministrativa:String,
  ): Response<ResponseApi>

  @GET("/lecturas/enviarLecturaFoto")
  suspend fun enviarFoto(
    @Header("Authorization") authorization: String,
    @Query("suministro") suministro:String,
    @Query("tipo") tipo:String,
    @Query("correlativo") correlativo:String,
    @Query("fecha") fecha:String,   //.replaceAll(' ', '%20')}'
    @Query("sucursal") sucursal:String,
    @Query("login") login:String
  ): Response<ResponseApi>

}























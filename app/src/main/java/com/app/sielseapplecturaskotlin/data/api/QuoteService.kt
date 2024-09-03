package com.app.sielseapplecturaskotlin.data.api

import android.util.Log
import com.app.sielseapplecturaskotlin.data.api.dto.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApliClient) {
  private val authorizationRequest = "Basic U2llbHNlTW92aWw6UzEzTFMzQEA="
  private val version = "1.0.7"

  suspend fun getListCategories(): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val loginRequest = "Basic U2llbHNlTW92aWw6UzEzTFMzQEA="
        val response: Response<ResponseApi> = api.listCategories(loginRequest);
        Log.e("Log List Categories", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error List Categories", "Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching categories".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun authenticationServices(user: String, password: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.authentication(
          authorization = authorizationRequest,
          user = user,
          password = password,
          version = version
        );
        Log.e("Log login", response.message().toString())
        response//461
      } catch (e: Exception) {
        Log.e("Error login", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching login".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun listaObservacioesServices(login: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.listaObservacioes(
          authorization = authorizationRequest,
          login = login
        );
        Log.e("Log listaObservacioes", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error listaObservacioes", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching listaObservacioes".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun librosPorCronogramaServices(login: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.librosPorCronograma(
          authorization = authorizationRequest,
          login = login
        );
        Log.e("Log librosPorCronograma", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error librosPorCronograma", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching librosPorCronograma".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun suministrosPorLibroCronogramaServices(
    login: String,
    libro: String,
    sucursal: String
  ): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.suministrosPorLibroCronograma(
          authorization = authorizationRequest,
          login = login,
          libro = libro,
          sucursal = sucursal
        );
        Log.e("Log suministrosPorLibroCronograma", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error suministrosPorLibroCronograma", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching suministrosPorLibroCronograma".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun librosSupervisionProgramadaServices(login: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.librosSupervisionProgramada(
          authorization = authorizationRequest,
          login = login
        );
        Log.e("Log librosSupervisionProgramada", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error librosSupervisionProgramada", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching librosSupervisionProgramada".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun suministrosPorLibroSupervisionProgramadaServices(
    login: String,
    libro: String,
    sucursal: String
  ): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.suministrosPorLibroSupervisionProgramada(
          authorization = authorizationRequest,
          login = login,
          libro = libro,
          sucursal = sucursal
        );
        Log.e("Log suministrosPorLibroSupervisionProgramada", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e(
          "Error suministrosPorLibroSupervisionProgramada",
          "Services Error occurred: ${e.message}",
          e
        )
        val errorResponseBody =
          "Error fetching suministrosPorLibroSupervisionProgramada".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun zonaProgramadaServices(login: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.zonaProgramada(
          authorization = authorizationRequest,
          login = login
        );
        Log.e("Log zonaProgramada", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error zonaProgramada", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching zonaProgramada".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun libroPorRutaServices(login: String, libro: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.libroPorRuta(
          authorization = authorizationRequest,
          login = login,
          libro = libro
        );
        Log.e("Log libroPorRuta", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error libroPorRuta", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching libroPorRuta".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun suministrosPorSupervisorLibroServices(
    login: String,
    libro: String,
    sucursal: String,
    zona: String
  ): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.suministrosPorSupervisorLibro(
          authorization = authorizationRequest,
          login = login,
          libro = libro,
          sucursal = sucursal,
          zona = zona
        );
        Log.e("Log suministrosPorSupervisorLibro", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error suministrosPorSupervisorLibro", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching suministrosPorSupervisorLibro".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun fechaHoraServidorServices(login: String): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.fechaHoraServidor(
          authorization = authorizationRequest,
          login = login
        );
        Log.e("Log fechaHoraServidor", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error fechaHoraServidor", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching fechaHoraServidor".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  //despachador datos http
  suspend fun enviarLecturaServices(
    login: String,
    codigoSuministro: String,
    tipo: String,
    lectura: String,
    observacion: String,
    secuenciaEjecutada: String,
    latitud: String,
    longitud: String,
    fecha: String,
    sucursal: String,
    codigoPeriodoComercial: String,
    codigoSucursal: String,
    codigoZonaAdministrativa: String
  ): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.enviarLectura(
          authorization = authorizationRequest,
          login = login,
          codigoSuministro = codigoSuministro,
          tipo = tipo,
          lectura = lectura,
          observacion = observacion,
          secuenciaEjecutada = secuenciaEjecutada,
          latitud = latitud,
          longitud = longitud,
          fecha = fecha.replace(" ","%20"),
          sucursal = sucursal,
          etiqueta1Valor = "app_version_2",
          codigoPeriodoComercial = codigoPeriodoComercial,
          codigoSucursal = codigoSucursal,
          codigoZonaAdministrativa = codigoZonaAdministrativa
        );
        Log.e("Log enviarLectura", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error enviarLectura", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching enviarLectura".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }

  suspend fun enviarFotoServices(
    login: String, suministro: String,
    tipo: String,
    correlativo: String,
    fecha: String,
    sucursal: String,
  ): Response<ResponseApi> {
    return withContext(Dispatchers.IO) {
      try {
        val response: Response<ResponseApi> = api.enviarFoto(
          authorization = authorizationRequest,
          suministro = suministro,
          tipo = tipo,
          correlativo = correlativo,
          fecha = fecha.replace(" ","%20"),
          sucursal = sucursal,
          login = login
        );
        Log.e("Log enviarFoto", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error enviarFoto", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching enviarFoto".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }
}
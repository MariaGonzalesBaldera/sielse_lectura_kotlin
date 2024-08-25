package com.app.sielseapplecturaskotlin.data.network

import android.util.Log
import com.app.sielseapplecturaskotlin.data.dto.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApliClient) {
  private val loginRequest = "Basic U2llbHNlTW92aWw6UzEzTFMzQEA="
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
          authorization = loginRequest,
          user = user,
          password = password,
          version = version
        );
        Log.e("Log login", response.message().toString())
        response
      } catch (e: Exception) {
        Log.e("Error login", "Services Error occurred: ${e.message}", e)
        val errorResponseBody =
          "Error fetching login".toResponseBody("text/plain".toMediaTypeOrNull())
        Response.error(500, errorResponseBody)
      }
    }
  }
}
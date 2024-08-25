package com.app.sielseapplecturaskotlin.data.repository

import android.util.Log
import com.app.sielseapplecturaskotlin.data.dto.ResponseApi
import com.app.sielseapplecturaskotlin.data.network.QuoteService
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(
  private val api: QuoteService
){
  suspend fun getCateriesRepository(): Response<ResponseApi> {
    val response: Response<ResponseApi> = api.getListCategories()
    Log.e("get categories", "repository " + response.message())
    return response
  }

  suspend fun getAuthentication(user:String, password:String): Response<ResponseApi> {
    val response: Response<ResponseApi> = api.authenticationServices(user,password)
    Log.e("get Authentication ", "repository Authentication " + response.message())
    return response
  }
}
package com.app.sielseapplecturaskotlin.di

import com.app.sielseapplecturaskotlin.data.api.QuoteApliClient
import com.app.sielseapplecturaskotlin.data.session.SessionManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Singleton
  @Provides
  fun providerRetrofit(): Retrofit {
    val sessionManager = SessionManager()

    val gson = GsonBuilder().create()
    val API_SERVER = "https://${sessionManager.getUrlServicio()}/${sessionManager.getApiName()}/"
    return Retrofit.Builder()
      .baseUrl(API_SERVER)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }
  @Singleton
  @Provides
  fun provideQuoteApiClient(retrofit: Retrofit): QuoteApliClient {
    return retrofit.create(QuoteApliClient::class.java)
  }
}
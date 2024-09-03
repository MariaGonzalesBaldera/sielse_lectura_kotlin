package com.app.sielseapplecturaskotlin.di

import android.content.Context
import androidx.room.Room
import com.app.sielseapplecturaskotlin.data.db.LecturaDatabase
import com.app.sielseapplecturaskotlin.data.db.dao.LecturaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  private const val QUOTE_DATABASE_NAME = "oms_lectura"

  @Singleton
  @Provides
  fun provideRoom(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, LecturaDatabase::class.java, QUOTE_DATABASE_NAME)
      .fallbackToDestructiveMigration().build()

  @Singleton
  @Provides
  fun provideUserDao(db: LecturaDatabase): LecturaDao = db.getLectura()

}
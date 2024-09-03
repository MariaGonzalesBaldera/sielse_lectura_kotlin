package com.app.sielseapplecturaskotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.app.sielseapplecturaskotlin.data.db.dao.LecturaDao
import com.app.sielseapplecturaskotlin.data.db.entity.Foto
import com.app.sielseapplecturaskotlin.data.db.entity.Libro
import com.app.sielseapplecturaskotlin.data.db.entity.Observacion
import com.app.sielseapplecturaskotlin.data.db.entity.Suministro
import com.google.android.gms.maps.model.LatLng

@Database(
  entities = [Foto::class, Libro::class, Observacion::class, Suministro::class],
  version = 1
)
@TypeConverters(Converters::class)
abstract class LecturaDatabase : RoomDatabase() {
  abstract fun getLectura(): LecturaDao
}


class Converters {

  @TypeConverter
  fun fromLatLng(latLng: LatLng?): String? {
    return latLng?.let { "${it.latitude},${it.longitude}" }
  }

  @TypeConverter
  fun toLatLng(latLngString: String?): LatLng? {
    return latLngString?.let {
      val parts = it.split(",")
      LatLng(parts[0].toDouble(), parts[1].toDouble())
    }
  }
}
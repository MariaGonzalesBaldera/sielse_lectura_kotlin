package com.app.sielseapplecturaskotlin.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.sielseapplecturaskotlin.data.db.entity.Foto
import com.app.sielseapplecturaskotlin.data.db.entity.Lectura
import com.app.sielseapplecturaskotlin.data.db.entity.Libro
import com.app.sielseapplecturaskotlin.data.db.entity.Observacion
import com.app.sielseapplecturaskotlin.data.db.entity.Suministro
import kotlinx.coroutines.flow.Flow

@Dao
interface LecturaDao {

  //LIBROS
  @Query("DELETE FROM libros")
  suspend fun deleteAllBooks(): Int

  @Query("SELECT * FROM libros")
  suspend fun getAll(): List<Libro>

  @Query("SELECT * FROM libros")
  fun watchLibros(): Flow<List<Libro>>

  @Query("SELECT * FROM libros WHERE codigo = :codigoLibro LIMIT 1")
  suspend fun libroPorCodigo(codigoLibro: Int): Libro?

  @Query("SELECT * FROM libros WHERE codigo = :codigoLibro AND tipo = :tipo LIMIT 1")
  suspend fun libroPorCodigoTipo(codigoLibro: Int, tipo: Int): Libro?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertLibros(libros: List<Libro>)

  @Query("SELECT COUNT(*) > 0 FROM libros WHERE ruta = :ruta AND tipo = :tipo")
  suspend fun existeLibroPorRutaYTipo(ruta: String, tipo: Int): Boolean

  //FOTOS
  @Query("SELECT COUNT(*) FROM fotos WHERE codigoSuministro = :codigoSuministro")
  suspend fun cantidadFotosPorSuministro(codigoSuministro: Int): Int

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFoto(foto: Foto): Long

  @Delete
  suspend fun deleteFoto(foto: Foto): Int

  @Query("SELECT MAX(numeroFoto) FROM fotos WHERE codigoSuministro = :codigoSuministro")
  suspend fun maxSuministroNumeroFoto(codigoSuministro: Int): Int?

  @Query("DELETE FROM fotos")
  suspend fun deleteAllPhoto(): Int

  @Query("SELECT * FROM fotos WHERE codigoSuministro = :codigoSuministro")
  suspend fun getFotosPorSuministro(codigoSuministro: Int): List<Foto>

  @Query("SELECT * FROM fotos")
  suspend fun listaTodasFotos(): List<Foto>

  @Query("SELECT * FROM fotos WHERE fotoEnviada = 0")
  suspend fun listaFotosNoEnviadas(): List<Foto>

  @Query("UPDATE fotos SET fotoEnviada = 1 WHERE codigoSuministro = :codigoSuministro AND numeroFoto = :numeroFoto")
  suspend fun actualizarComoEnviada(codigoSuministro: Int, numeroFoto: Int): Int

  //OBSERVATIONS
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertObservacion(observacion: Observacion): Long

  @Query("DELETE FROM observacions")
  suspend fun deleteAllObservation(): Int

  @Query("SELECT * FROM observacions WHERE codigo = :codigoObservacion LIMIT 1")
  suspend fun observacionPorCodigo(codigoObservacion: Int): Observacion?

  @Query("SELECT * FROM observacions")
  suspend fun getAllObservations(): List<Observacion>

  @Query("SELECT * FROM observacions")
  fun watchObservacions(): kotlinx.coroutines.flow.Flow<List<Observacion>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertObservaciones(listaObservacion: List<Observacion>)

  //SUMINISTRO
  @Query("SELECT * FROM suministros")
  suspend fun getAllSuministro(): List<Suministro>

  @Query("SELECT * FROM suministros WHERE codigoLibro = :codigoLibro")
  fun watchSuministrosPorLibro(codigoLibro: Int): Flow<List<Suministro>>

  @Query("""
        SELECT * FROM suministros 
        WHERE codigoLibro = :codigoLibro AND (
            (:tipoFiltro = 0 AND rutaSuministro LIKE '%' || :filtro || '%') OR
            (:tipoFiltro = 1 AND CAST(codigoSuministro AS TEXT) LIKE '%' || :filtro || '%') OR
            (:tipoFiltro = 2 AND medidor LIKE '%' || :filtro || '%')
        ) 
        AND (:pendientes = 0 OR fechaLectura IS NULL)
        ORDER BY secuenciaPropuesta ASC
    """)
  fun suministrosPorLibroYFiltro(
    codigoLibro: Int, tipoFiltro: Int, filtro: String, pendientes: Boolean
  ): Flow<List<Suministro>>

  @Query("SELECT * FROM suministros WHERE codigoSuministro = :codigo")
  fun watchSuministro(codigo: Int): Flow<Suministro>

  @Query("SELECT * FROM suministros WHERE codigoSuministro = :codigo")
  suspend fun suministroPorCodigo(codigo: Int): Suministro

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPadron(listaSuministro: List<Suministro>)

  @Query("DELETE FROM suministros")
  suspend fun deleteAll(): Int

  @Query("SELECT MAX(secuenciaEjecutada) FROM suministros WHERE codigoLibro = :codigoLibro")
  suspend fun siguienteSecuenciaPropuesta(codigoLibro: Int): Int?

  @Query("""
        SELECT MIN(secuenciaPropuesta) 
        FROM suministros 
        WHERE codigoLibro = :codigoLibro AND fechaLectura IS NULL AND secuenciaPropuesta > :secuenciaActual
    """)
  suspend fun buscarSiguienteSecuenciaLecturar(
    codigoLibro: Int, secuenciaActual: Int
  ): Int?

  @Query("""
        SELECT MIN(secuenciaPropuesta) 
        FROM suministros 
        WHERE codigoLibro = :codigoLibro AND fechaLectura IS NULL AND secuenciaPropuesta < :secuenciaActual
    """)
  suspend fun buscarAnteriorSecuenciaLecturar(
    codigoLibro: Int, secuenciaActual: Int
  ): Int?

  @Transaction
  suspend fun siguienteSuministroALecturar(
    codigoLibro: Int, secuenciaActual: Int
  ): Suministro {
    val siguienteSecuencia = buscarSiguienteSecuenciaLecturar(codigoLibro, secuenciaActual)
      ?: buscarAnteriorSecuenciaLecturar(codigoLibro, secuenciaActual)
      ?: throw Exception("No existen suministros para lecturar")
    return suministroPorSecuencia(codigoLibro, siguienteSecuencia)
  }

  @Query("""
        SELECT * 
        FROM suministros 
        WHERE codigoLibro = :codigoLibro AND secuenciaPropuesta = :secuencia
    """)
  suspend fun suministroPorSecuencia(codigoLibro: Int, secuencia: Int): Suministro

  @Update
  suspend fun actualizarSuministro(suministro: Suministro)

  @Transaction
  suspend fun actualizarLectura(suministro: Suministro, lectura: Lectura): Suministro {
    val nuevoSuministro = suministro.copy(
      lectura = lectura.lectura!!,
      observacion = lectura.observacion,
      secuenciaEjecutada = lectura.secuenciaEjecutada,
      lecturaEnviada = lectura.lecturaEnviada,
      fechaLectura = lectura.fechaLectura!!,
      latitudSuministro = lectura.posicionActual!!.latitude,
      longitudSuministro = lectura.posicionActual!!.longitude
    )
    actualizarSuministro(nuevoSuministro)
    return nuevoSuministro
  }

  @Query("""
        UPDATE suministros 
        SET lecturaEnviada = 1 
        WHERE codigoSuministro = :codigo
    """)
  suspend fun actualizarComoEnviada(codigo: Int)

  @Query("""
        SELECT * 
        FROM suministros 
        WHERE codigoLibro = :codigoLibro AND (:soloPendientes = 0 OR fechaLectura IS NULL)
    """)
  suspend fun pinSuministrosPorLibro(
    codigoLibro: Int, soloPendientes: Boolean
  ): List<Suministro>

  @Query("SELECT * FROM suministros WHERE codigoSuministro = :codigoSuministro")
  suspend fun pinSuministroPorSuministro(codigoSuministro: Int): List<Suministro>
}
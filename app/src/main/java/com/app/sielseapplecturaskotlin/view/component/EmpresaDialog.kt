package com.app.sielseapplecturaskotlin.view.component

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.sielseapplecturaskotlin.data.api.dto.Empresa

@Preview
@Composable
private fun prevDialog() {
  val list:List<Empresa> = listOf(
    Empresa(a=1, nombre="Electro Sur Este S.A.A.", url="appsrv.else.com.pe"),
      Empresa(a=2, nombre="ElectroPuno S.A.A.", url="mobapp.electropuno.com.pe"),
      Empresa(a=3, nombre="SEAL", url="appsrv.seal.com.pe"),
      Empresa(a=4, nombre="ELECTROSUR S.A.", url="appserv.electrosur.com.pe"),
      Empresa(a=5, nombre="Electro Ucayali", url="www.electroucayali.com.pe"),
      Empresa(a=6, nombre="ADINELSA", url="adinelsawebapp.else.com.pe"),
      Empresa(a=7, nombre="ELECTROSUR S.A.(PRUEBAS)" , url="elsx021v.electrosur.com.pe:4333"),
      Empresa(a=8, "nombre=Electro Ucayali(V2)", url="www.electroucayali.com.pe"),
      Empresa(a=9, nombre="ELSE-PRUEBAS", url="appsrvdev.else.com.pe")
  )
    ;
  EmpresaDialog(list,{},{})
}


@Composable
fun EmpresaDialog(
  empresas: List<Empresa>,
  onDismiss: () -> Unit,
  onEmpresaSelected: (Empresa) -> Unit
) {
  Log.e("empresas dialog",empresas.toString())
  AlertDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      Button(onClick = onDismiss) {
        Text("Cerrar")
      }
    },
    text = {
      CompanyList(empresas = empresas, onEmpresaSelected = onEmpresaSelected)
    }
  )
}

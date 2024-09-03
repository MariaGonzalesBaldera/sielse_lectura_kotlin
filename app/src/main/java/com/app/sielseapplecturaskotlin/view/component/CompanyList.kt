package com.app.sielseapplecturaskotlin.view.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.sielseapplecturaskotlin.data.api.dto.Empresa

@Preview(showBackground = true)
@Composable
private fun prevCOmpany() {
  val list: List<Empresa> = listOf(
    Empresa(a=1, nombre="Electro Sur Este S.A.A.", url="appsrv.else.com.pe"),
    Empresa(a=2, nombre="ElectroPuno S.A.A.", url="mobapp.electropuno.com.pe"),
    Empresa(a=3, nombre="SEAL", url="appsrv.seal.com.pe"),
    Empresa(a=4, nombre="ELECTROSUR S.A.", url="appserv.electrosur.com.pe"),
    Empresa(a=5, nombre="Electro Ucayali", url="www.electroucayali.com.pe"),
    Empresa(a=6, nombre="ADINELSA", url="adinelsawebapp.else.com.pe"),
    Empresa(a=7, nombre="ELECTROSUR S.A.(PRUEBAS)" , url="elsx021v.electrosur.com.pe:4333"),
    Empresa(a=8, "nombre=Electro Ucayali(V2)", url="www.electroucayali.com.pe"),
    Empresa(a=9, nombre="ELSE-PRUEBAS", url="appsrvdev.else.com.pe")
  );
  CompanyList(list, {})
}

@Composable
fun CompanyList(empresas: List<Empresa>, onEmpresaSelected: (Empresa) -> Unit) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
      .height(250.dp)
  ) {
    Text(
      text = "SELECCIONE UNA EMPRESA",
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(bottom = 0.dp)
    )
    LazyColumn(modifier = Modifier.padding(top = 5.dp)) {
      items(empresas) { empresa ->
        EmpresaItem(
          empresa = empresa,
          onClick = { onEmpresaSelected(empresa) }
        )
      }
    }
  }
}

@Composable
fun EmpresaItem(
  empresa: Empresa,
  onClick: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .padding(vertical = 1.dp, horizontal = 0.dp)
  ) {
    Text(
      text = empresa.nombre,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(5.dp)
    )
    HorizontalDivider()
  }
}
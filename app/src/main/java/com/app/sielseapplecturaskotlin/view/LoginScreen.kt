package com.app.sielseapplecturaskotlin.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.app.sielseapplecturaskotlin.view.component.BotonDefault
import com.app.sielseapplecturaskotlin.view.component.TransparentTextField
import com.app.sielseapplecturaskotlin.R
import com.app.sielseapplecturaskotlin.data.dto.Empresa
import com.app.sielseapplecturaskotlin.navigation.AppScreens
import com.app.sielseapplecturaskotlin.utils.toast
import com.app.sielseapplecturaskotlin.view.component.EmpresaDialog
import com.app.sielseapplecturaskotlin.view.component.ProgressDialogLoading
import com.app.sielseapplecturaskotlin.viewModel.LecturaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComponent(navController: NavController,lecturaViewModel: LecturaViewModel) {
  val context = LocalContext.current
  val focusManager = LocalFocusManager.current
  var passwordVisibility by remember { mutableStateOf(false) }
  val checkedStateValue = remember { ("0") }
  var checkedState by remember { mutableStateOf("1") }
  val userValue = remember { mutableStateOf("") }
  val passwordValue = remember { mutableStateOf("") }
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  var dialogResponse by remember { mutableStateOf(false) }
  var showDialog by remember { mutableStateOf(false) }
  var companyList by remember { mutableStateOf<List<Empresa>>(emptyList()) }
  val isUserValid = remember { mutableStateOf(true) }
  val isPasswordValid = remember { mutableStateOf(true) }

  val titleCompany = remember { mutableStateOf("Seleccione su empresa") }

  lecturaViewModel.validModel.observe(lifecycleOwner) { value ->
    if (value == true) {
      dialogResponse = false
     navController.navigate(route = AppScreens.ScreenListBooks.route)
    } else {
      dialogResponse = false
      context.toast("Credenciales inválidas")
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = colorResource(id = R.color.primary)
        ),
        title = {
          Text(
            text = "SIELSE LECTURAS",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        }
      )
    },
    bottomBar = {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 80.dp),
        contentAlignment = Alignment.Center
      ) {
        BotonDefault(
          modifier = Modifier.padding(horizontal = 80.dp),
          title = "INGRESAR",
          onClick = {
            isUserValid.value = userValue.value.isNotEmpty()
            isPasswordValid.value = passwordValue.value.isNotEmpty()

            if (isUserValid.value && isPasswordValid.value) {
              if(titleCompany.value=="" || titleCompany.value=="Seleccione su empresa"){
                context.toast("Seleccione una empresa")
              }else{
                lecturaViewModel.authentication(context,"mtito","pwd123")
               // lecturaViewModel.authentication(context,userValue.value,passwordValue.value)
              }
            }
          })
        ProgressDialogLoading(onDismiss = { dialogResponse = false }, showProgress = dialogResponse)

      }
    },
    content = { paddingValues ->
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .background(color = Color.White)
      ) {
        item {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(paddingValues)
          ) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                .fillMaxWidth()
            ) {
              Text(
                text = "Ingrese sus Credenciales",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                  .padding(top = 30.dp, bottom = 20.dp)
              )
            }
            Column(
              modifier = Modifier
                .padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              Column {
                TransparentTextField(
                  textFieldValue = userValue,
                  textLabel = "Usuario",
                  keyboardType = KeyboardType.Text,
                  keyboardActions = KeyboardActions(
                    onNext = {
                      focusManager.moveFocus(FocusDirection.Down)
                    }
                  ),
                  imeAction = ImeAction.Next,
                  isValid = isUserValid

                )
                Spacer(
                  modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp)
                )
                TransparentTextField(
                  textFieldValue = passwordValue,
                  textLabel = "Contraseña",
                  keyboardType = KeyboardType.Password,
                  keyboardActions = KeyboardActions(
                    onDone = {
                      focusManager.clearFocus()
                    }
                  ),
                  imeAction = ImeAction.Done,
                  trailingIcon = {
                    IconButton(
                      onClick = {
                        passwordVisibility = !passwordVisibility
                      }
                    ) {

                      Icon(
                        painter = if (passwordVisibility) {
                          painterResource(id = R.drawable.ic_visibility)
                        } else {
                          painterResource(id = R.drawable.ic_visibility_off)
                        },
                        contentDescription = "Toggle Password Icon"
                      )
                    }
                  },
                  visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None
                  } else {
                    PasswordVisualTransformation()
                  },
                  isValid = isPasswordValid

                )
              }
              Spacer(
                modifier = Modifier.padding(5.dp)
              )
              Row(
                modifier = Modifier

                  .padding(vertical = 20.dp, horizontal = 10.dp)
                  .fillMaxSize()
                  .background(color = Color.White)
                  .clickable { },
                verticalAlignment = Alignment.CenterVertically
              ) {
                Checkbox(
                  checked = true,
                  onCheckedChange = {

                  },
                  colors = CheckboxDefaults.colors(colorResource(id = R.color.purple))
                )
                Text(text = "Recordar mis credenciales")
              }
              Spacer(
                modifier = Modifier.padding(5.dp)
              )

              //lista desplegable
              Row(
                modifier = Modifier

                  .padding(vertical = 20.dp, horizontal = 10.dp)
                  .fillMaxSize()
                  .background(color = Color.White)
                  .clickable { },
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  OutlinedButton(onClick = { showDialog = true }) {
                    Text(titleCompany.value)
                  }

                  if (showDialog) {
                    lecturaViewModel.getCategories()
                    lecturaViewModel.categories.observe(lifecycleOwner) { empresas ->
                      companyList = empresas
                    }

                    EmpresaDialog(
                      empresas = companyList,
                      onDismiss = { showDialog = false },
                      onEmpresaSelected = { empresa ->
                        titleCompany.value = empresa.nombre
                        Log.d("Selected Empresa", empresa.toString())
                        lecturaViewModel.selectCompany(empresa.url.toString())
                        showDialog = false
                      }
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  )
}
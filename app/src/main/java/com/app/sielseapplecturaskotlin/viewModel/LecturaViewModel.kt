package com.app.sielseapplecturaskotlin.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.sielseapplecturaskotlin.data.api.dto.Empresa
import com.app.sielseapplecturaskotlin.data.db.entity.User
import com.app.sielseapplecturaskotlin.data.preferences.UserPreferences
import com.app.sielseapplecturaskotlin.data.repository.QuoteRepository
import com.app.sielseapplecturaskotlin.data.session.SessionManager
import com.app.sielseapplecturaskotlin.useCase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturaViewModel @Inject constructor(
  private var getCategoriesUseCase: GetCategoriesUseCase,
  private var sessionManager: SessionManager,
  private var repository: QuoteRepository,
  var userPreferences: UserPreferences
) : ViewModel() {

  //verificar si existe usuario guardado en memoria
  private val _userCredentials = MutableStateFlow<User?>(null)
  val userCredentials: StateFlow<User?> get() = _userCredentials
  init {
    viewModelScope.launch {
      val user = userPreferences.getUserCredentials()
      _userCredentials.value = user
    }
  }

  private val _categories = MutableLiveData<List<Empresa>>()
  val categories: LiveData<List<Empresa>> get() = _categories

  @SuppressLint("NullSafeMutableLiveData")
  fun getCategories() {
    viewModelScope.launch {
      try {
        val empresas = getCategoriesUseCase.getCategories()
        _categories.value = empresas
      } catch (e: Exception) {
        Log.e("ViewModel", "Error fetching categories", e)
      }
    }
  }

  fun selectCompany(urlSelect: String) {
    viewModelScope.launch {
      sessionManager.saveUrlServicio(urlSelect)
    }
  }


  //login
  private val _loginSuccess = MutableLiveData<Boolean>()
  val loginSuccess: LiveData<Boolean> get() = _loginSuccess

  fun authentication(context: Context, user: String, password: String) {
    viewModelScope.launch {
      val response = repository.getAuthentication(
        user = user,
        password = password,
        context = context
      )
      _loginSuccess.postValue(response)
      if (response) {
        userPreferences.saveUserCredentials(
          login = user,
          empresa = sessionManager.getEmpresa(),
          password = password,
          urlServicio = sessionManager.getUrlServicio(),
          verSoloPendientes = false,
          envioLecturasAuto = true,
          envioFotoAuto = true
        )
      }
    }
  }
}
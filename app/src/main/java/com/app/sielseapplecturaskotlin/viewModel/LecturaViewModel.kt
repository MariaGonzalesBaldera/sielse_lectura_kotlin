package com.app.sielseapplecturaskotlin.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.sielseapplecturaskotlin.data.dto.Empresa
import com.app.sielseapplecturaskotlin.data.session.SessionManager
import com.app.sielseapplecturaskotlin.useCase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturaViewModel @Inject constructor(
  private var getCategoriesUseCase: GetCategoriesUseCase,
  private var sessionManager: SessionManager,

) : ViewModel() {
  private val _categories = MutableLiveData<List<Empresa>>()
  val categories: LiveData<List<Empresa>> get() = _categories

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
  fun selectCompany(urlSelect : String){
    viewModelScope.launch{
      sessionManager.saveUrlServicio(urlSelect)
    }
  }
  fun authentication(context:Context){
    viewModelScope.launch {
      val login = getCategoriesUseCase.getAuthentication(context,"mtito","pwd123")
      Log.e("login view", login.toString())
    }
  }
}
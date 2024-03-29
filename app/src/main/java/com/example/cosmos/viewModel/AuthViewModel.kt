package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cosmos.repo.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepo) : ViewModel(){

    private val _login = MutableLiveData<String>()
    val login : LiveData<String>
        get() = _login

    private val _signUp = MutableLiveData<String>()
    val signUp : LiveData<String>
        get() = _signUp


    fun login(email :String, pass : String){
        _login.postValue("Loading")
        viewModelScope.launch {
            _login.postValue(repo.login(email,pass))
        }
    }

    fun signUp(email: String, userName : String , pass : String, name :String){
        _signUp.postValue("Loading")
        viewModelScope.launch {
            _signUp.postValue(repo.signUp(email,pass,userName,name))
        }
    }
}
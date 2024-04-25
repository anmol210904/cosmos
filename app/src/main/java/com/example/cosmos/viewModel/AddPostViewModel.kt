package com.example.cosmos.viewModel

import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.api.resource.Response
import com.example.cosmos.repo.AddPostRepo
import kotlinx.coroutines.launch

class AddPostViewModel(private val repo: AddPostRepo) : ViewModel() {

    private  val _addPostResult = MutableLiveData<Response<String>>()
    val addPostResult : LiveData<Response<String>>
        get() = _addPostResult

    fun addImage(imageUri : Uri,desp : String){
        _addPostResult.postValue(Response.Loading())
        viewModelScope.launch {
            _addPostResult.postValue(repo.addPost(imageUri,desp))
        }
    }
}
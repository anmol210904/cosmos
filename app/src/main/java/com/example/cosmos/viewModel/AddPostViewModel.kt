package com.example.cosmos.viewModel

import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.repo.AddPostRepo
import kotlinx.coroutines.launch

class AddPostViewModel(private val repo: AddPostRepo) : ViewModel() {

    fun addImage(imageUri : Uri,desp : String){
        viewModelScope.launch {
            repo.addPost(imageUri,desp)
        }
    }
}
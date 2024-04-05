package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.models.post.PostModel
import com.example.cosmos.repo.GetPostRepo
import kotlinx.coroutines.launch

class GetPostViewModel(private val repo : GetPostRepo): ViewModel() {

    val _posts = MutableLiveData<ArrayList<GetPostModel>?>()
    val posts : LiveData<ArrayList<GetPostModel>?>
        get() = _posts

    fun getPosts(){
        _posts.postValue(null)
        viewModelScope.launch {
            _posts.postValue(repo.getPosts())
        }
    }


    init {
        _posts.postValue(null)
    }
}
package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.PostModel
import com.example.cosmos.repo.ProfileRepo
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: ProfileRepo): ViewModel() {
    private val _posts = MutableLiveData<ArrayList<PostModel>?>()
    val posts : LiveData<ArrayList<PostModel>?>
        get() = _posts

    private val _profile = MutableLiveData<SignUpModel?>()
    val profile : LiveData<SignUpModel?>
        get() = _profile


    fun getProfile(uid : String){
        _profile.postValue(null)
        viewModelScope.launch {
            _profile.postValue(repo.getProfile(uid))
        }
    }

    fun getPosts(uid : String){
        _posts.postValue(null)
        viewModelScope.launch {
            _posts.postValue(repo.getPosts(uid))
        }
    }


    init {
        _posts.postValue(null)
        _profile.postValue(null)
    }

}
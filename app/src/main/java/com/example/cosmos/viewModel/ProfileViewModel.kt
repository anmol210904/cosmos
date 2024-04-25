package com.example.cosmos.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.models.post.PostModel
import com.example.cosmos.repo.ProfileRepo
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: ProfileRepo): ViewModel() {
    private val _posts = MutableLiveData<ArrayList<GetPostModel>?>()
    val posts : LiveData<ArrayList<GetPostModel>?>
        get() = _posts

    private val _profile = MutableLiveData<SignUpModel?>()
    val profile : LiveData<SignUpModel?>
        get() = _profile

    private val _editUserResponse = MutableLiveData<Response<Unit>>()
    val editUserResponse : LiveData<Response<Unit>>
        get() = _editUserResponse


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



    fun editProfile(
        username : String , fullName : String , image : Uri?
    ){
        _editUserResponse.postValue(Response.Loading())
        viewModelScope.launch {
            _editUserResponse.postValue(repo.editUser(username, fullName, image))
        }


    }


    init {
        _posts.postValue(null)
        _profile.postValue(null)
    }

}
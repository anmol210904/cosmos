package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.repo.PostRepo
import kotlinx.coroutines.launch

class PostViewModel(private val repo: PostRepo) : ViewModel() {

    private val _post = MutableLiveData<GetPostModel?>()
    val post: MutableLiveData<GetPostModel?>
        get() = _post


    fun getPost(postId: String) {

        _post.postValue(null)
        viewModelScope.launch {
            post.postValue(repo.getPost(postId))
        }

    }

    fun addLike(postId: String){
        viewModelScope.launch {
            repo.addLike(postId);
        }
    }

    fun removeLike(postId: String){
        viewModelScope.launch {
            repo.removeLike(postId)
        }
    }

//    fun addComment()

}

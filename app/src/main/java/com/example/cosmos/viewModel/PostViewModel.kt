package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.post.CommentModel
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.repo.PostRepo
import kotlinx.coroutines.launch

class PostViewModel(private val repo: PostRepo) : ViewModel() {

    private val _post = MutableLiveData<GetPostModel?>()
    val post: MutableLiveData<GetPostModel?>
        get() = _post


    private val _commentResponse = MutableLiveData<Response<Unit>>()
    val commentResponse : LiveData<Response<Unit>>
        get() = _commentResponse


    private val _loadCommentsResponse = MutableLiveData<Response<ArrayList<CommentModel>>>()
    val loadCommentResponse : LiveData<Response<ArrayList<CommentModel>>>
        get() = _loadCommentsResponse


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


    fun addComment(postId: String, commentModel: CommentModel){
        _commentResponse.postValue(Response.Loading())
        viewModelScope.launch {
            _commentResponse.postValue(repo.addComment(postId , commentModel))
            _loadCommentsResponse.postValue(repo.loadComments(postId))

        }
    }


    fun loadComments(postId: String){
        _loadCommentsResponse.postValue(Response.Loading())

        viewModelScope.launch {
            _loadCommentsResponse.postValue(repo.loadComments(postId))
        }
    }






}

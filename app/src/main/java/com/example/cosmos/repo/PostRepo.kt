package com.example.cosmos.repo

import androidx.compose.runtime.DisposableEffect
import com.example.cosmos.models.post.GetPostModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostRepo() {

    suspend fun getPost(postId: String): GetPostModel {
        var temp = GetPostModel();
        CoroutineScope(Dispatchers.IO).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnCompleteListener {
                if (it.isSuccessful) {

                    temp = it.result.toObject(GetPostModel::class.java)!!

                }
            }.await()
        }.join()
        return temp;

    }

    suspend fun addLike(postId: String){
        CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnSuccessListener{
                var likes : Long = it.get("likes").toString().toLong()
                likes++;
                Firebase.firestore.collection("posts").document(postId).update("likes",likes);

            }
        }
    }

    suspend fun removeLike(postId: String){
        CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnSuccessListener{
                var likes : Long = it.get("likes").toString().toLong()
                likes--;
                Firebase.firestore.collection("posts").document(postId).update("likes",likes);

            }
        }
    }

}